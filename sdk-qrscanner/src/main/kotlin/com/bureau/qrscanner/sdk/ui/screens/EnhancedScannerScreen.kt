package com.bureau.qrscanner.sdk.ui.screens

import android.Manifest
import android.util.Log
import android.widget.Toast
import androidx.camera.core.Camera
import androidx.camera.core.CameraSelector
import androidx.camera.core.FocusMeteringAction
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import androidx.camera.core.Preview
import androidx.camera.core.SurfaceOrientedMeteringPointFactory
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.FlashOff
import androidx.compose.material.icons.filled.FlashOn
import androidx.compose.material.icons.filled.Help
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.common.InputImage
import com.bureau.qrscanner.sdk.ScannerConfig
import com.bureau.qrscanner.sdk.ui.state.rememberHelpOverlayState
import com.bureau.qrscanner.sdk.ui.components.BureauFooter
import kotlinx.coroutines.delay
import java.util.concurrent.TimeUnit

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun EnhancedScannerScreen(
    primaryColor: Color,
    scannerConfig: ScannerConfig,
    onQrCodeScanned: (String) -> Unit,
    onClosePressed: () -> Unit,
    onTimeout: () -> Unit
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    
    // State management
    var timeoutSeconds by remember { mutableIntStateOf(scannerConfig.timeoutSeconds) }
    var isFlashOn by remember { mutableStateOf(false) }
    var camera by remember { mutableStateOf<Camera?>(null) }
    var isQrDetected by remember { mutableStateOf(false) }
    var hasScanned by remember { mutableStateOf(false) }
    
    // Help overlay state
    val helpOverlayState = rememberHelpOverlayState(
        initialShowOverlay = scannerConfig.showFirstTimeHelp
    )
    
    // Camera permission state
    val cameraPermissionState = rememberPermissionState(Manifest.permission.CAMERA)
    
    // Request camera permission on first launch
    LaunchedEffect(Unit) {
        if (!cameraPermissionState.status.isGranted) {
            cameraPermissionState.launchPermissionRequest()
        }
    }
    
    // Handle permission denial
    LaunchedEffect(cameraPermissionState.status) {
        if (!cameraPermissionState.status.isGranted && 
            !cameraPermissionState.status.shouldShowRationale) {
            Toast.makeText(
                context,
                "Camera permission is required to scan QR code.",
                Toast.LENGTH_LONG
            ).show()
            onClosePressed()
        }
    }
    
    // Countdown timer
    LaunchedEffect(cameraPermissionState.status.isGranted) {
        if (cameraPermissionState.status.isGranted) {
            while (timeoutSeconds > 0 && !hasScanned) {
                delay(1000)
                timeoutSeconds--
            }
            if (timeoutSeconds <= 0 && !hasScanned) {
                Toast.makeText(context, "Capture Timeout", Toast.LENGTH_SHORT).show()
                onTimeout()
            }
        }
    }
    
    // Flash control
    LaunchedEffect(isFlashOn, camera) {
        camera?.let { cam ->
            if (cam.cameraInfo.hasFlashUnit()) {
                cam.cameraControl.enableTorch(isFlashOn)
            }
        }
    }

    when {
        cameraPermissionState.status.isGranted -> {
            // Camera permission granted - show camera
            Box(modifier = Modifier.fillMaxSize()) {
                // Camera Preview
                AndroidView(
                    factory = { context ->
                        PreviewView(context).apply {
                            scaleType = PreviewView.ScaleType.FILL_CENTER
                        }
                    },
                    modifier = Modifier.fillMaxSize()
                ) { previewView ->
                    val cameraProviderFuture = ProcessCameraProvider.getInstance(context)
                    cameraProviderFuture.addListener({
                        try {
                            val cameraProvider = cameraProviderFuture.get()
                            
                            val preview = Preview.Builder().build().also {
                                it.setSurfaceProvider(previewView.surfaceProvider)
                            }
                            
                            val imageAnalyzer = ImageAnalysis.Builder()
                                .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                                .build()
                                .also {
                                    it.setAnalyzer(ContextCompat.getMainExecutor(context)) { imageProxy ->
                                        if (!hasScanned) {
                                            processImageProxy(
                                                imageProxy = imageProxy,
                                                onQrCodeScanned = { qrData ->
                                                    hasScanned = true
                                                    onQrCodeScanned(qrData)
                                                },
                                                onQrDetected = { detected -> isQrDetected = detected }
                                            )
                                        } else {
                                            imageProxy.close()
                                        }
                                    }
                                }
                            
                            val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA
                            
                            cameraProvider.unbindAll()
                            camera = cameraProvider.bindToLifecycle(
                                lifecycleOwner,
                                cameraSelector,
                                preview,
                                imageAnalyzer
                            )
                            
                            // Setup auto-focus
                            if (scannerConfig.enableAutoFocus) {
                                setupAutoFocus(camera!!, previewView)
                            }
                            
                        } catch (exc: Exception) {
                            Log.e("CameraX", "Camera initialization failed", exc)
                        }
                    }, ContextCompat.getMainExecutor(context))
                }

                // Enhanced Overlay with animated frame
                EnhancedQrScannerOverlay(
                    primaryColor = primaryColor,
                    isAnimationEnabled = scannerConfig.frameAnimationEnabled,
                    isQrDetected = isQrDetected,
                    modifier = Modifier.fillMaxSize()
                )

                // Top controls
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .statusBarsPadding(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Top
                ) {
                    // Close button
                    IconButton(
                        onClick = onClosePressed,
                        modifier = Modifier
                            .size(48.dp)
                            .background(Color.Black.copy(alpha = 0.6f), CircleShape)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Close",
                            tint = Color.White,
                            modifier = Modifier.size(24.dp)
                        )
                    }

                    // Flashlight toggle
                    if (scannerConfig.enableFlashToggle) {
                        FlashlightToggle(
                            isFlashOn = isFlashOn,
                            camera = camera,
                            onToggle = { isFlashOn = it }
                        )
                    }
                }

                // Timeout indicator
                AnimatedVisibility(
                    visible = timeoutSeconds <= 30,
                    enter = slideInVertically() + fadeIn(),
                    exit = slideOutVertically() + fadeOut()
                ) {
                    Box(
                        modifier = Modifier
                            .align(Alignment.TopCenter)
                            .padding(top = 80.dp)
                            .background(
                                if (timeoutSeconds <= 10) Color.Red.copy(alpha = 0.9f) 
                                else Color(0xFFFF9800).copy(alpha = 0.8f),
                                RoundedCornerShape(20.dp)
                            )
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                    ) {
                        Text(
                            text = "Timeout: ${formatTime(timeoutSeconds)}",
                            color = Color.White,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                // Help overlay with animation
                AnimatedVisibility(
                    visible = helpOverlayState.isOverlayVisible,
                    enter = scaleIn(
                        animationSpec = spring(
                            dampingRatio = Spring.DampingRatioMediumBouncy,
                            stiffness = Spring.StiffnessLow
                        )
                    ) + fadeIn(),
                    exit = scaleOut(
                        targetScale = 0.1f,
                        animationSpec = spring(
                            dampingRatio = Spring.DampingRatioMediumBouncy,
                            stiffness = Spring.StiffnessLow
                        )
                    ) + fadeOut()
                ) {
                    FirstTimeHelpOverlay(
                        onDismiss = { helpOverlayState.dismissOverlay() }
                    )
                }

                // Help icon in bottom right
                AnimatedVisibility(
                    visible = helpOverlayState.isHelpIconVisible,
                    enter = scaleIn(
                        animationSpec = spring(
                            dampingRatio = Spring.DampingRatioMediumBouncy,
                            stiffness = Spring.StiffnessLow
                        )
                    ) + fadeIn(),
                    exit = scaleOut() + fadeOut(),
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(16.dp)
                ) {
                    FloatingActionButton(
                        onClick = { helpOverlayState.showOverlay() },
                        modifier = Modifier.size(56.dp),
                        containerColor = primaryColor,
                        contentColor = Color.White
                    ) {
                        Icon(
                            imageVector = Icons.Default.Help,
                            contentDescription = "Help",
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }

                // Footer
                BureauFooter(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(bottom = if (helpOverlayState.isHelpIconVisible) 80.dp else 16.dp)
                )
            }
        }
        
        cameraPermissionState.status.shouldShowRationale -> {
            PermissionRationaleScreen(
                onRequestPermission = { cameraPermissionState.launchPermissionRequest() },
                onClose = onClosePressed
            )
        }
        
        else -> {
            PermissionDeniedScreen(
                onRequestPermission = { cameraPermissionState.launchPermissionRequest() },
                onClose = onClosePressed
            )
        }
    }
}

@Composable
private fun FirstTimeHelpOverlay(
    onDismiss: () -> Unit
) {
    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true
        )
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // QR Code illustration
                Box(
                    modifier = Modifier
                        .size(120.dp)
                        .background(Color.Gray.copy(alpha = 0.1f), RoundedCornerShape(12.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "📱",
                        fontSize = 48.sp
                    )
                }
                
                Spacer(modifier = Modifier.height(16.dp))
                
                Text(
                    text = "Align the QR code within the frame and hold your device steady for a quick scan.",
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    color = Color.Black,
                    lineHeight = 22.sp
                )
                
                Spacer(modifier = Modifier.height(24.dp))
                
                Button(
                    onClick = onDismiss,
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF007AFF)
                    )
                ) {
                    Text(
                        text = "Done",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }
    }
}

@Composable
private fun FlashlightToggle(
    isFlashOn: Boolean,
    camera: Camera?,
    onToggle: (Boolean) -> Unit
) {
    val scale by animateFloatAsState(
        targetValue = if (isFlashOn) 1.1f else 1f,
        animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy)
    )
    
    IconButton(
        onClick = { 
            camera?.let { cam ->
                if (cam.cameraInfo.hasFlashUnit()) {
                    onToggle(!isFlashOn)
                }
            }
        },
        modifier = Modifier
            .size(48.dp)
            .scale(scale)
            .background(
                if (isFlashOn) Color.Yellow.copy(alpha = 0.8f) 
                else Color.Black.copy(alpha = 0.6f), 
                CircleShape
            )
    ) {
        Icon(
            imageVector = if (isFlashOn) Icons.Default.FlashOn else Icons.Default.FlashOff,
            contentDescription = if (isFlashOn) "Flash Off" else "Flash On",
            tint = if (isFlashOn) Color.Black else Color.White,
            modifier = Modifier.size(24.dp)
        )
    }
}

@Composable
private fun EnhancedQrScannerOverlay(
    primaryColor: Color,
    isAnimationEnabled: Boolean,
    isQrDetected: Boolean,
    modifier: Modifier = Modifier
) {
    val animatedCornerLength by animateFloatAsState(
        targetValue = if (isQrDetected) 40f else 30f,
        animationSpec = tween(300)
    )
    
    val pulseAnimation by rememberInfiniteTransition().animateFloat(
        initialValue = 0.6f,
        targetValue = 1.0f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000),
            repeatMode = RepeatMode.Reverse
        )
    )
    
    Canvas(modifier = modifier) {
        val canvasWidth = size.width
        val canvasHeight = size.height
        
        // Frame dimensions
        val frameSize = minOf(canvasWidth, canvasHeight) * 0.65f
        val frameLeft = (canvasWidth - frameSize) / 2
        val frameTop = (canvasHeight - frameSize) / 2
        val frameRight = frameLeft + frameSize
        val frameBottom = frameTop + frameSize
        
        // Draw overlay (dark areas outside frame)
        val overlayColor = Color.Black.copy(alpha = 0.6f)
        
        // Top
        drawRect(
            color = overlayColor,
            topLeft = androidx.compose.ui.geometry.Offset(0f, 0f),
            size = androidx.compose.ui.geometry.Size(canvasWidth, frameTop)
        )
        
        // Bottom
        drawRect(
            color = overlayColor,
            topLeft = androidx.compose.ui.geometry.Offset(0f, frameBottom),
            size = androidx.compose.ui.geometry.Size(canvasWidth, canvasHeight - frameBottom)
        )
        
        // Left
        drawRect(
            color = overlayColor,
            topLeft = androidx.compose.ui.geometry.Offset(0f, frameTop),
            size = androidx.compose.ui.geometry.Size(frameLeft, frameSize)
        )
        
        // Right
        drawRect(
            color = overlayColor,
            topLeft = androidx.compose.ui.geometry.Offset(frameRight, frameTop),
            size = androidx.compose.ui.geometry.Size(canvasWidth - frameRight, frameSize)
        )
        
        // Draw animated frame corners
        val cornerLength = if (isAnimationEnabled) animatedCornerLength.dp.toPx() else 30.dp.toPx()
        val cornerWidth = 4.dp.toPx()
        val frameColor = if (isQrDetected) Color.Green else primaryColor
        val alpha = if (isAnimationEnabled && !isQrDetected) pulseAnimation else 1f
        
        // Draw all four corners
        drawFrameCorners(
            frameLeft = frameLeft,
            frameTop = frameTop,
            frameRight = frameRight,
            frameBottom = frameBottom,
            cornerLength = cornerLength,
            cornerWidth = cornerWidth,
            color = frameColor,
            alpha = alpha
        )
        
        // Draw scanning line animation
        if (isAnimationEnabled && !isQrDetected) {
            val scanLineY = frameTop + (frameSize * (pulseAnimation % 1f))
            drawRect(
                color = primaryColor.copy(alpha = 0.7f),
                topLeft = androidx.compose.ui.geometry.Offset(frameLeft, scanLineY),
                size = androidx.compose.ui.geometry.Size(frameSize, 2.dp.toPx())
            )
        }
    }
}

private fun androidx.compose.ui.graphics.drawscope.DrawScope.drawFrameCorners(
    frameLeft: Float,
    frameTop: Float,
    frameRight: Float,
    frameBottom: Float,
    cornerLength: Float,
    cornerWidth: Float,
    color: Color,
    alpha: Float
) {
    // Top-left corner
    drawRect(
        color = color.copy(alpha = alpha),
        topLeft = androidx.compose.ui.geometry.Offset(frameLeft, frameTop),
        size = androidx.compose.ui.geometry.Size(cornerLength, cornerWidth)
    )
    drawRect(
        color = color.copy(alpha = alpha),
        topLeft = androidx.compose.ui.geometry.Offset(frameLeft, frameTop),
        size = androidx.compose.ui.geometry.Size(cornerWidth, cornerLength)
    )
    
    // Top-right corner
    drawRect(
        color = color.copy(alpha = alpha),
        topLeft = androidx.compose.ui.geometry.Offset(frameRight - cornerLength, frameTop),
        size = androidx.compose.ui.geometry.Size(cornerLength, cornerWidth)
    )
    drawRect(
        color = color.copy(alpha = alpha),
        topLeft = androidx.compose.ui.geometry.Offset(frameRight - cornerWidth, frameTop),
        size = androidx.compose.ui.geometry.Size(cornerWidth, cornerLength)
    )
    
    // Bottom-left corner
    drawRect(
        color = color.copy(alpha = alpha),
        topLeft = androidx.compose.ui.geometry.Offset(frameLeft, frameBottom - cornerWidth),
        size = androidx.compose.ui.geometry.Size(cornerLength, cornerWidth)
    )
    drawRect(
        color = color.copy(alpha = alpha),
        topLeft = androidx.compose.ui.geometry.Offset(frameLeft, frameBottom - cornerLength),
        size = androidx.compose.ui.geometry.Size(cornerWidth, cornerLength)
    )
    
    // Bottom-right corner
    drawRect(
        color = color.copy(alpha = alpha),
        topLeft = androidx.compose.ui.geometry.Offset(frameRight - cornerLength, frameBottom - cornerWidth),
        size = androidx.compose.ui.geometry.Size(cornerLength, cornerWidth)
    )
    drawRect(
        color = color.copy(alpha = alpha),
        topLeft = androidx.compose.ui.geometry.Offset(frameRight - cornerWidth, frameBottom - cornerLength),
        size = androidx.compose.ui.geometry.Size(cornerWidth, cornerLength)
    )
}

private fun setupAutoFocus(camera: Camera, previewView: PreviewView) {
    val factory = SurfaceOrientedMeteringPointFactory(
        previewView.width.toFloat(),
        previewView.height.toFloat()
    )
    
    val centerPoint = factory.createPoint(
        previewView.width / 2f,
        previewView.height / 2f
    )
    
    val focusAction = FocusMeteringAction.Builder(centerPoint)
        .setAutoCancelDuration(3, TimeUnit.SECONDS)
        .build()
    
    camera.cameraControl.startFocusAndMetering(focusAction)
}

@Composable
private fun PermissionRationaleScreen(
    onRequestPermission: () -> Unit,
    onClose: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(32.dp),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "📷 Camera Permission Required",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                
                Text(
                    text = "This app needs camera access to scan QR codes. Please grant camera permission to continue.",
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    color = Color.Gray,
                    modifier = Modifier.padding(bottom = 24.dp)
                )
                
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    OutlinedButton(
                        onClick = onClose,
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("Cancel")
                    }
                    
                    Button(
                        onClick = onRequestPermission,
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("Grant Permission")
                    }
                }
            }
        }
    }
}

@Composable
private fun PermissionDeniedScreen(
    onRequestPermission: () -> Unit,
    onClose: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(32.dp),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "⚠️ Camera Permission Denied",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                
                Text(
                    text = "Camera access is required to scan QR codes. Please enable camera permission in your device settings.",
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    color = Color.Gray,
                    modifier = Modifier.padding(bottom = 24.dp)
                )
                
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    OutlinedButton(
                        onClick = onClose,
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("Close")
                    }
                    
                    Button(
                        onClick = onRequestPermission,
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("Try Again")
                    }
                }
            }
        }
    }
}

private fun processImageProxy(
    imageProxy: ImageProxy, 
    onQrCodeScanned: (String) -> Unit,
    onQrDetected: (Boolean) -> Unit
) {
    val mediaImage = imageProxy.image
    if (mediaImage != null) {
        val image = InputImage.fromMediaImage(mediaImage, imageProxy.imageInfo.rotationDegrees)
        
        val scanner = BarcodeScanning.getClient()
        scanner.process(image)
            .addOnSuccessListener { barcodes ->
                if (barcodes.isNotEmpty()) {
                    onQrDetected(true)
                    for (barcode in barcodes) {
                        when (barcode.valueType) {
                            Barcode.TYPE_TEXT -> {
                                barcode.rawValue?.let { value ->
                                    onQrCodeScanned(value)
                                }
                            }
                            Barcode.TYPE_URL -> {
                                barcode.rawValue?.let { value ->
                                    onQrCodeScanned(value)
                                }
                            }
                        }
                    }
                } else {
                    onQrDetected(false)
                }
            }
            .addOnFailureListener {
                onQrDetected(false)
            }
            .addOnCompleteListener {
                imageProxy.close()
            }
    } else {
        imageProxy.close()
    }
}

private fun formatTime(seconds: Int): String {
    val minutes = seconds / 60
    val remainingSeconds = seconds % 60
    return String.format("%02d:%02d", minutes, remainingSeconds)
} 