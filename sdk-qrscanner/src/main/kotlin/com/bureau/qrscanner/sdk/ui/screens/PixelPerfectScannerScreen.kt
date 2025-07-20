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
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.Image
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FlashOff
import androidx.compose.material.icons.filled.FlashOn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.geometry.Size
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
import com.bureau.qrscanner.sdk.utils.AadhaarQrParser
import com.bureau.qrscanner.sdk.utils.QrFrameDetector
import kotlinx.coroutines.delay
import java.util.concurrent.TimeUnit
import com.bureau.qrscanner.sdk.R
import com.bureau.qrscanner.sdk.ui.Variables

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun PixelPerfectScannerScreen(
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
    
    // Enhanced QR frame detection state
    var qrFrameDetection by remember { mutableStateOf<QrFrameDetector.DetectionResult?>(null) }
    var previewSize by remember { mutableStateOf<androidx.compose.ui.geometry.Size?>(null) }
    
    // Help overlay state
    val helpOverlayState = rememberHelpOverlayState(
        initialShowOverlay = scannerConfig.showFirstTimeHelp
    )
    
    // Camera permission state
    val cameraPermissionState = rememberPermissionState(Manifest.permission.CAMERA)
    
    // Request camera permission on first launch
    LaunchedEffect(Unit) {
        Log.d("QrScanner", "=== Checking camera permission ===")
        Log.d("QrScanner", "Permission granted: ${cameraPermissionState.status.isGranted}")
        if (!cameraPermissionState.status.isGranted) {
            Log.d("QrScanner", "Requesting camera permission...")
            cameraPermissionState.launchPermissionRequest()
        }
    }
    
    // Handle permission denial
    LaunchedEffect(cameraPermissionState.status) {
        Log.d("QrScanner", "Permission status changed: ${cameraPermissionState.status}")
        Log.d("QrScanner", "Should show rationale: ${cameraPermissionState.status.shouldShowRationale}")
        if (!cameraPermissionState.status.isGranted && 
            !cameraPermissionState.status.shouldShowRationale) {
            Log.w("QrScanner", "❌ Camera permission denied permanently")
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
        Log.d("QrScanner", "=== Starting countdown timer ===")
        Log.d("QrScanner", "Initial timeout: ${timeoutSeconds}s, Permission granted: ${cameraPermissionState.status.isGranted}")
        if (cameraPermissionState.status.isGranted) {
            while (timeoutSeconds > 0 && !hasScanned) {
                delay(1000)
                timeoutSeconds--
                Log.d("QrScanner", "Countdown: ${timeoutSeconds}s remaining, hasScanned: $hasScanned")
            }
            if (timeoutSeconds <= 0 && !hasScanned) {
                Log.w("QrScanner", "❌ Timeout reached!")
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
                    // Capture preview size when available (using ViewTreeObserver)
                    if (previewView.width > 0 && previewView.height > 0 && previewSize == null) {
                        previewSize = Size(previewView.width.toFloat(), previewView.height.toFloat())
                        Log.d("QrScanner", "✓ Preview size captured: ${previewSize}")
                    } else {
                        Log.d("QrScanner", "Preview size not available yet - width: ${previewView.width}, height: ${previewView.height}, current previewSize: $previewSize")
                    }
                    
                    Log.d("QrScanner", "=== Camera Setup Started ===")
                    val cameraProviderFuture = ProcessCameraProvider.getInstance(context)
                    cameraProviderFuture.addListener({
                        try {
                            Log.d("QrScanner", "Getting camera provider...")
                            val cameraProvider = cameraProviderFuture.get()
                            Log.d("QrScanner", "Camera provider obtained successfully")
                            
                            Log.d("QrScanner", "Creating preview...")
                            val preview = Preview.Builder().build().also {
                                it.setSurfaceProvider(previewView.surfaceProvider)
                                Log.d("QrScanner", "Preview surface provider set")
                            }
                            
                            Log.d("QrScanner", "Setting up ImageAnalysis...")
                            val imageAnalyzer = ImageAnalysis.Builder()
                                .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                                .build()
                                .also {
                                    Log.d("QrScanner", "Setting analyzer on ImageAnalysis...")
                                    it.setAnalyzer(ContextCompat.getMainExecutor(context)) { imageProxy ->
                                        Log.d("QrScanner", "ImageAnalyzer called - hasScanned: $hasScanned")
                                        if (!hasScanned) {
                                            processImageProxy(
                                                imageProxy = imageProxy,
                                                onQrCodeScanned = { qrData ->
                                                    Log.d("QrScanner", "✓ QR Code scanned successfully!")
                                                    hasScanned = true
                                                    // Show success toast
                                                    Toast.makeText(
                                                        context,
                                                        "QR Code Scanned Successfully!",
                                                        Toast.LENGTH_SHORT
                                                    ).show()
                                                    onQrCodeScanned(qrData)
                                                },
                                                onQrDetected = { detected -> 
                                                    Log.d("QrScanner", "QR detected state changed: $detected")
                                                    isQrDetected = detected 
                                                },
                                                onFrameDetection = { detection -> 
                                                    Log.d("QrScanner", "Frame detection updated: $detection")
                                                    qrFrameDetection = detection 
                                                },
                                                previewSize = previewSize
                                            )
                                        } else {
                                            Log.d("QrScanner", "Already scanned, closing imageProxy")
                                            imageProxy.close()
                                        }
                                    }
                                }
                            
                            Log.d("QrScanner", "Selecting back camera...")
                            val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA
                            
                            Log.d("QrScanner", "Unbinding all cameras...")
                            cameraProvider.unbindAll()
                            
                            Log.d("QrScanner", "Binding camera to lifecycle...")
                            camera = cameraProvider.bindToLifecycle(
                                lifecycleOwner,
                                cameraSelector,
                                preview,
                                imageAnalyzer
                            )
                            Log.d("QrScanner", "✓ Camera bound successfully!")
                            
                            // Setup auto-focus
                            if (scannerConfig.enableAutoFocus) {
                                Log.d("QrScanner", "Setting up auto-focus...")
                                setupAutoFocus(camera!!, previewView)
                                Log.d("QrScanner", "✓ Auto-focus configured")
                            } else {
                                Log.d("QrScanner", "Auto-focus disabled in config")
                            }
                            
                        } catch (exc: Exception) {
                            Log.e("QrScanner", "❌ Camera initialization FAILED", exc)
                            Log.e("QrScanner", "Exception type: ${exc.javaClass.simpleName}")
                            Log.e("QrScanner", "Exception message: ${exc.message}")
                        }
                    }, ContextCompat.getMainExecutor(context))
                }

                // Top Bar - EXACTLY like original design
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .statusBarsPadding()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Close button (X) - left side
                    IconButton(
                        onClick = onClosePressed,
                        modifier = Modifier
                            .size(40.dp)
                            .background(
                                Color.Black.copy(alpha = 0.6f), 
                                CircleShape
                            )
                    ) {
                        Icon(
                            imageVector = ImageVector.vectorResource(R.drawable.ic_close),
                            contentDescription = "Close",
                            tint = Color.White,
                            modifier = Modifier.size(20.dp)
                        )
                    }

                    // Timeout indicator - center (CRITICAL - this was missing!)
                    Row(
                        modifier = Modifier
                            .background(
                                Color.Black.copy(alpha = 0.6f),
                                RoundedCornerShape(20.dp)
                            )
                            .padding(horizontal = 12.dp, vertical = 6.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            imageVector = ImageVector.vectorResource(R.drawable.ic_clock),
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "Timeout in ${formatTime(timeoutSeconds)}",
                            color = Color.White,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }

                    // Flash button - right side
                    if (scannerConfig.enableFlashToggle) {
                        IconButton(
                            onClick = { 
                                camera?.let { cam ->
                                    if (cam.cameraInfo.hasFlashUnit()) {
                                        isFlashOn = !isFlashOn
                                    }
                                }
                            },
                            modifier = Modifier
                                .size(40.dp)
                                .background(
                                    if (isFlashOn) Color.Yellow.copy(alpha = 0.9f) 
                                    else Color.Black.copy(alpha = 0.6f), 
                                    CircleShape
                                )
                        ) {
                            Icon(
                                imageVector = if (isFlashOn) Icons.Default.FlashOn else Icons.Default.FlashOff,
                                contentDescription = if (isFlashOn) "Flash Off" else "Flash On",
                                tint = if (isFlashOn) Color.Black else Color.White,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                    } else {
                        // Placeholder to maintain layout balance
                        Spacer(modifier = Modifier.size(40.dp))
                    }
                }

                // Scanning frame with RED/GREEN borders based on QR position
                PixelPerfectScanningFrame(
                    isQrDetected = isQrDetected,
                    frameDetection = qrFrameDetection,
                    isAnimationEnabled = scannerConfig.frameAnimationEnabled,
                    modifier = Modifier.fillMaxSize()
                )

                // QR Scan Header Section
                Column(
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .offset(y = 120.dp)
                        .padding(horizontal = 32.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // QR Scan Title
                    Text(
                        text = "QR Scan",
                        style = TextStyle(
                            fontSize = Variables.FontSizeDisplayXs,
                            lineHeight = Variables.LineHeightDisplayXs,
                            fontFamily = FontFamily.Default,
                            fontWeight = FontWeight(600),
                            color = Variables.TextColorPrimary,
                        )
                    )
                    
                    Spacer(modifier = Modifier.height(8.dp))
                    
                    // Description Text
                    Text(
                        text = "Please scan to capture your government IDs QR",
                        style = TextStyle(
                            fontSize = Variables.FontSizeTextSm,
                            lineHeight = Variables.LineHeightTextSm,
                            fontFamily = FontFamily.Default,
                            fontWeight = FontWeight(400),
                            color = Variables.TextColorPrimary,
                        ),
                        textAlign = TextAlign.Center
                    )
                    
                    Spacer(modifier = Modifier.height(16.dp))
                    
                    // Document Types Container
                    Box(
                        modifier = Modifier
                            .border(
                                width = 1.dp, 
                                color = Variables.ColorsBorder.BorderSecondary
                            )
                            .width(278.dp)
                            .height(72.dp)
                            .padding(
                                start = Variables.spacing3Xl, 
                                top = Variables.spacingXl, 
                                end = Variables.spacing3Xl, 
                                bottom = Variables.spacingXl
                            )
                            .background(
                                Color.White.copy(alpha = 0.9f),
                                RoundedCornerShape(8.dp)
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxSize()
                        ) {
                            // Aadhaar
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.ic_file),
                                    contentDescription = "Aadhaar",
                                    modifier = Modifier.size(24.dp)
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = "Aadhaar",
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Medium,
                                    color = Variables.TextColorPrimary
                                )
                            }
                            
                            // PAN
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.ic_file),
                                    contentDescription = "PAN",
                                    modifier = Modifier.size(24.dp)
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = "PAN",
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Medium,
                                    color = Variables.TextColorPrimary
                                )
                            }
                            
                            // Voter ID
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.ic_file),
                                    contentDescription = "Voter ID",
                                    modifier = Modifier.size(24.dp)
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = "Voter ID",
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Medium,
                                    color = Variables.TextColorPrimary
                                )
                            }
                        }
                    }
                    
                    Spacer(modifier = Modifier.height(24.dp))
                    
                    // Scanning instruction
                    Box(
                        modifier = Modifier
                            .background(
                                Color.Black.copy(alpha = 0.7f),
                                RoundedCornerShape(12.dp)
                            )
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_qr_code),
                                contentDescription = "QR Code",
                                modifier = Modifier.size(16.dp),
                                colorFilter = androidx.compose.ui.graphics.ColorFilter.tint(Color.White)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "Keep the QR code within the frame",
                                color = Color.White,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Medium,
                                textAlign = TextAlign.Center
                            )
                        }
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
                    PixelPerfectHelpOverlay(
                        onDismiss = { helpOverlayState.dismissOverlay() }
                    )
                }

                // Help icon in bottom right - BLUE background with WHITE text (fixed!)
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
                    IconButton(
                        onClick = { helpOverlayState.showOverlay() },
                        modifier = Modifier
                            .size(48.dp)
                            .background(
                                Color(0xFF5A67D8), // Blue background like in the images
                                CircleShape
                            )
                    ) {
                        Text(
                            text = "?",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White // White text on blue background
                        )
                    }
                }

                // Footer - exact design
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
private fun PixelPerfectHelpOverlay(
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
                .width(300.dp)
                .padding(horizontal = 32.dp, vertical = 48.dp),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
        ) {
            Column(
                modifier = Modifier.padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_qr_code_help),
                    contentDescription = "QR Code",
                    modifier = Modifier
                        .size(80.dp)
                        .padding(vertical = 8.dp),
                    contentScale = ContentScale.Fit
                )
                
                Spacer(modifier = Modifier.height(12.dp))
                
                Text(
                    text = "Align the QR code within the frame and hold your device steady for a quick scan.",
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center,
                    color = Color.Black,
                    lineHeight = 20.sp,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier.padding(horizontal = 4.dp)
                )
                
                Spacer(modifier = Modifier.height(16.dp))
                
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .background(
                            Color(0xFF6C63FF),
                            CircleShape
                        )
                )
                
                Spacer(modifier = Modifier.height(20.dp))
                
                Button(
                    onClick = onDismiss,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(44.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF5A67D8)
                    ),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(
                        text = "Done",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White
                    )
                }
            }
        }
    }
}

@Composable
private fun PixelPerfectScanningFrame(
    isQrDetected: Boolean,
    frameDetection: QrFrameDetector.DetectionResult?,
    isAnimationEnabled: Boolean,
    modifier: Modifier = Modifier
) {
    val animatedCornerLength by animateFloatAsState(
        targetValue = if (isQrDetected) 40f else 30f,
        animationSpec = tween(300)
    )
    
    Canvas(modifier = modifier) {
        val canvasWidth = size.width
        val canvasHeight = size.height
        
        // Frame dimensions - pixel perfect positioning
        val frameSize = minOf(canvasWidth, canvasHeight) * 0.7f
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
        
        // Draw frame corners - Dynamic RED/GREEN colors based on QR position
        val cornerLength = if (isAnimationEnabled) animatedCornerLength.dp.toPx() else 30.dp.toPx()
        val cornerWidth = 4.dp.toPx()
        
        // Determine frame color based on QR position
        val frameColor = frameDetection?.let { detection ->
            QrFrameDetector.getBorderColor(detection)
        } ?: Color.White  // Default to white if no detection
        
        // Determine alpha based on QR position
        val alpha = frameDetection?.let { detection ->
            QrFrameDetector.getBorderAlpha(detection)
        } ?: 1f  // Full opacity by default
        
        // Draw all four corners with dynamic colors
        drawPixelPerfectCorners(
            frameLeft = frameLeft,
            frameTop = frameTop,
            frameRight = frameRight,
            frameBottom = frameBottom,
            cornerLength = cornerLength,
            cornerWidth = cornerWidth,
            color = frameColor,
            alpha = alpha
        )
    }
}

private fun androidx.compose.ui.graphics.drawscope.DrawScope.drawPixelPerfectCorners(
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
    drawRoundRect(
        color = color.copy(alpha = alpha),
        topLeft = androidx.compose.ui.geometry.Offset(frameLeft, frameTop),
        size = androidx.compose.ui.geometry.Size(cornerLength, cornerWidth),
        cornerRadius = androidx.compose.ui.geometry.CornerRadius(2.dp.toPx())
    )
    drawRoundRect(
        color = color.copy(alpha = alpha),
        topLeft = androidx.compose.ui.geometry.Offset(frameLeft, frameTop),
        size = androidx.compose.ui.geometry.Size(cornerWidth, cornerLength),
        cornerRadius = androidx.compose.ui.geometry.CornerRadius(2.dp.toPx())
    )
    
    // Top-right corner
    drawRoundRect(
        color = color.copy(alpha = alpha),
        topLeft = androidx.compose.ui.geometry.Offset(frameRight - cornerLength, frameTop),
        size = androidx.compose.ui.geometry.Size(cornerLength, cornerWidth),
        cornerRadius = androidx.compose.ui.geometry.CornerRadius(2.dp.toPx())
    )
    drawRoundRect(
        color = color.copy(alpha = alpha),
        topLeft = androidx.compose.ui.geometry.Offset(frameRight - cornerWidth, frameTop),
        size = androidx.compose.ui.geometry.Size(cornerWidth, cornerLength),
        cornerRadius = androidx.compose.ui.geometry.CornerRadius(2.dp.toPx())
    )
    
    // Bottom-left corner
    drawRoundRect(
        color = color.copy(alpha = alpha),
        topLeft = androidx.compose.ui.geometry.Offset(frameLeft, frameBottom - cornerWidth),
        size = androidx.compose.ui.geometry.Size(cornerLength, cornerWidth),
        cornerRadius = androidx.compose.ui.geometry.CornerRadius(2.dp.toPx())
    )
    drawRoundRect(
        color = color.copy(alpha = alpha),
        topLeft = androidx.compose.ui.geometry.Offset(frameLeft, frameBottom - cornerLength),
        size = androidx.compose.ui.geometry.Size(cornerWidth, cornerLength),
        cornerRadius = androidx.compose.ui.geometry.CornerRadius(2.dp.toPx())
    )
    
    // Bottom-right corner
    drawRoundRect(
        color = color.copy(alpha = alpha),
        topLeft = androidx.compose.ui.geometry.Offset(frameRight - cornerLength, frameBottom - cornerWidth),
        size = androidx.compose.ui.geometry.Size(cornerLength, cornerWidth),
        cornerRadius = androidx.compose.ui.geometry.CornerRadius(2.dp.toPx())
    )
    drawRoundRect(
        color = color.copy(alpha = alpha),
        topLeft = androidx.compose.ui.geometry.Offset(frameRight - cornerWidth, frameBottom - cornerLength),
        size = androidx.compose.ui.geometry.Size(cornerWidth, cornerLength),
        cornerRadius = androidx.compose.ui.geometry.CornerRadius(2.dp.toPx())
    )
}

private fun setupAutoFocus(camera: Camera, previewView: PreviewView) {
    try {
        Log.d("QrScanner", "Setting up auto-focus - preview size: ${previewView.width}x${previewView.height}")
        
        val factory = SurfaceOrientedMeteringPointFactory(
            previewView.width.toFloat(),
            previewView.height.toFloat()
        )
        Log.d("QrScanner", "Created metering point factory")
        
        val centerPoint = factory.createPoint(
            previewView.width / 2f,
            previewView.height / 2f
        )
        Log.d("QrScanner", "Created center point for focus")
        
        val focusAction = FocusMeteringAction.Builder(centerPoint)
            .setAutoCancelDuration(3, TimeUnit.SECONDS)
            .build()
        Log.d("QrScanner", "Created focus action")
        
        camera.cameraControl.startFocusAndMetering(focusAction)
        Log.d("QrScanner", "✓ Auto-focus started successfully")
    } catch (exc: Exception) {
        Log.e("QrScanner", "❌ Auto-focus setup failed", exc)
        Log.e("QrScanner", "Exception type: ${exc.javaClass.simpleName}")
        Log.e("QrScanner", "Exception message: ${exc.message}")
    }
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
    onQrDetected: (Boolean) -> Unit,
    onFrameDetection: (QrFrameDetector.DetectionResult?) -> Unit = {},
    previewSize: Size? = null
) {
    Log.d("QrScanner", "=== processImageProxy called ===")
    Log.d("QrScanner", "ImageProxy info: ${imageProxy.width}x${imageProxy.height}, rotation: ${imageProxy.imageInfo.rotationDegrees}")
    Log.d("QrScanner", "PreviewSize: $previewSize")
    
    val mediaImage = imageProxy.image
    if (mediaImage != null) {
        Log.d("QrScanner", "MediaImage info: ${mediaImage.width}x${mediaImage.height}, format: ${mediaImage.format}")
        Log.d("QrScanner", "Creating InputImage from MediaImage...")
        
        val image = InputImage.fromMediaImage(mediaImage, imageProxy.imageInfo.rotationDegrees)
        Log.d("QrScanner", "InputImage created successfully")
        
        val scanner = BarcodeScanning.getClient()
        Log.d("QrScanner", "Starting barcode scanning...")
        
        scanner.process(image)
            .addOnSuccessListener { barcodes ->
                Log.d("QrScanner", "=== Barcode scanning SUCCESS ===")
                Log.d("QrScanner", "Found ${barcodes.size} barcodes")
                
                if (barcodes.isNotEmpty()) {
                    Log.d("QrScanner", "Processing ${barcodes.size} barcodes...")
                    onQrDetected(true)
                    
                    // Process frame detection for the first barcode
                    val firstBarcode = barcodes.first()
                    Log.d("QrScanner", "First barcode info - Format: ${firstBarcode.format}, ValueType: ${firstBarcode.valueType}")
                    Log.d("QrScanner", "Barcode bounds: ${firstBarcode.boundingBox}")
                    
                    val frameDetection = processQrFrameDetection(
                        barcode = firstBarcode,
                        imageWidth = mediaImage.width,
                        imageHeight = mediaImage.height,
                        previewSize = previewSize
                    )
                    
                    Log.d("QrScanner", "Frame detection result: $frameDetection")
                    onFrameDetection(frameDetection)
                    
                    // Only proceed with scanning if QR is in frame or close to frame
                    val shouldScan = frameDetection?.let { detection ->
                        val result = detection.isInFrame || detection.framePosition == QrFrameDetector.FramePosition.OVERLAPPING
                        Log.d("QrScanner", "Should scan based on frame detection: $result (isInFrame: ${detection.isInFrame}, framePosition: ${detection.framePosition})")
                        result
                    } ?: true.also { Log.d("QrScanner", "No frame detection, defaulting to allow scanning") }
                    
                    if (shouldScan) {
                        Log.d("QrScanner", "Processing barcodes for scanning...")
                        for ((index, barcode) in barcodes.withIndex()) {
                            Log.d("QrScanner", "Processing barcode $index: ${barcode.format}")
                            barcode.rawValue?.let { rawValue ->
                                Log.d("QrScanner", "Barcode $index - Type: ${barcode.valueType}, Format: ${barcode.format}")
                                Log.d("QrScanner", "Raw value length: ${rawValue.length}")
                                Log.d("QrScanner", "Raw value preview: ${rawValue.take(100)}...")
                                
                                // Check if it's an Aadhaar QR code
                                if (AadhaarQrParser.isAadhaarQr(rawValue)) {
                                    Log.d("QrScanner", "✓ Detected as Aadhaar QR code")
                                    handleAadhaarQrCode(rawValue, onQrCodeScanned)
                                } else {
                                    Log.d("QrScanner", "✓ Detected as regular QR code")
                                    // Handle regular QR codes
                                    when (barcode.valueType) {
                                        Barcode.TYPE_TEXT -> {
                                            Log.d("QrScanner", "✓ Processing as Text QR code")
                                            onQrCodeScanned(rawValue)
                                        }
                                        Barcode.TYPE_URL -> {
                                            Log.d("QrScanner", "✓ Processing as URL QR code")
                                            onQrCodeScanned(rawValue)
                                        }
                                        else -> {
                                            Log.d("QrScanner", "✓ Processing as Other QR code type: ${barcode.valueType}")
                                            onQrCodeScanned(rawValue)
                                        }
                                    }
                                }
                            } ?: Log.w("QrScanner", "Barcode $index has null rawValue")
                        }
                    } else {
                        Log.w("QrScanner", "❌ QR detected but NOT in frame - skipping scan")
                    }
                } else {
                    Log.d("QrScanner", "No barcodes found in this frame")
                    onQrDetected(false)
                    onFrameDetection(null)
                }
            }
            .addOnFailureListener { exception ->
                Log.e("QrScanner", "❌ Barcode scanning FAILED", exception)
                Log.e("QrScanner", "Exception type: ${exception.javaClass.simpleName}")
                Log.e("QrScanner", "Exception message: ${exception.message}")
                onQrDetected(false)
                onFrameDetection(null)
            }
            .addOnCompleteListener {
                Log.d("QrScanner", "Barcode scanning completed, closing imageProxy")
                imageProxy.close()
            }
    } else {
        Log.w("QrScanner", "❌ MediaImage is NULL - cannot process")
        imageProxy.close()
    }
}

/**
 * Process QR frame detection for visual feedback
 */
private fun processQrFrameDetection(
    barcode: com.google.mlkit.vision.barcode.common.Barcode,
    imageWidth: Int,
    imageHeight: Int,
    previewSize: Size?
): QrFrameDetector.DetectionResult? {
    Log.d("QrScanner", "=== Processing QR Frame Detection ===")
    Log.d("QrScanner", "Image dimensions: ${imageWidth}x${imageHeight}")
    Log.d("QrScanner", "Preview size: $previewSize")
    
    if (previewSize == null) {
        Log.w("QrScanner", "❌ Preview size is null, cannot perform frame detection")
        return null
    }
    
    // Convert barcode bounds to QR bounds
    Log.d("QrScanner", "Converting barcode bounds to QR bounds...")
    val qrBounds = QrFrameDetector.convertBarcodeToQrBounds(
        barcode = barcode,
        imageWidth = imageWidth,
        imageHeight = imageHeight,
        previewWidth = previewSize.width.toInt(),
        previewHeight = previewSize.height.toInt()
    )
    
    if (qrBounds == null) {
        Log.w("QrScanner", "❌ Failed to convert barcode bounds to QR bounds")
        return null
    }
    
    Log.d("QrScanner", "QR bounds: $qrBounds")
    
    // Calculate frame bounds
    Log.d("QrScanner", "Calculating frame bounds...")
    val frameBounds = QrFrameDetector.calculateFrameBounds(previewSize)
    Log.d("QrScanner", "Frame bounds: $frameBounds")
    
    // Detect QR position
    Log.d("QrScanner", "Detecting QR position relative to frame...")
    val result = QrFrameDetector.detectQrPosition(qrBounds, frameBounds)
    Log.d("QrScanner", "✓ Frame detection result: $result")
    
    return result
}

/**
 * Handle Aadhaar QR code scanning with proper parsing and privacy measures
 */
private fun handleAadhaarQrCode(
    rawValue: String,
    onQrCodeScanned: (String) -> Unit
) {
    Log.d("QrScanner", "=== Handling Aadhaar QR Code ===")
    Log.d("QrScanner", "Raw value length: ${rawValue.length}")
    Log.d("QrScanner", "Raw QR data preview: ${rawValue.take(200)}...")
    Log.d("QrScanner", "Raw QR data type: ${rawValue.javaClass.simpleName}")
    
    Log.d("QrScanner", "Parsing Aadhaar QR code...")
    val aadhaarData = AadhaarQrParser.parseAadhaarQr(rawValue)
    
    if (aadhaarData != null) {
        Log.d("QrScanner", "✓ Parsed Aadhaar data successfully")
        Log.d("QrScanner", "Parsed data - Name: ${aadhaarData.name}")
        Log.d("QrScanner", "Parsed data - DOB: ${aadhaarData.dob}")
        Log.d("QrScanner", "Parsed data - Gender: ${aadhaarData.gender}")
        Log.d("QrScanner", "Parsed data - UID: ${aadhaarData.uid.take(4)}****")
        Log.d("QrScanner", "Parsed data - State: ${aadhaarData.state}")
        Log.d("QrScanner", "Parsed data - District: ${aadhaarData.district}")
        
        // Validate Aadhaar UID
        Log.d("QrScanner", "Validating Aadhaar UID...")
        if (AadhaarQrParser.isValidAadhaarUid(aadhaarData.uid)) {
            Log.d("QrScanner", "✓ Valid Aadhaar UID found")
            
            // For privacy, mask the UID and return structured data
            val maskedUid = AadhaarQrParser.maskAadhaarUid(aadhaarData.uid)
            val formattedAddress = AadhaarQrParser.formatAddress(aadhaarData)
            
            Log.d("QrScanner", "Masked UID: $maskedUid")
            Log.d("QrScanner", "Formatted address: $formattedAddress")
            
            // Create a JSON response for better handling
            val aadhaarResponse = """
                {
                    "type": "aadhaar",
                    "uid": "$maskedUid",
                    "name": "${aadhaarData.name}",
                    "gender": "${aadhaarData.gender}",
                    "dob": "${aadhaarData.dob}",
                    "address": "$formattedAddress",
                    "mobile": "${aadhaarData.mobile}",
                    "email": "${aadhaarData.email}",
                    "pincode": "${aadhaarData.pincode}",
                    "state": "${aadhaarData.state}",
                    "district": "${aadhaarData.district}",
                    "isValid": true,
                    "rawData": "${rawValue.take(500)}..."
                }
            """.trimIndent()
            
            Log.d("QrScanner", "✓ Sending Aadhaar response")
            onQrCodeScanned(aadhaarResponse)
        } else {
            Log.w("QrScanner", "❌ Invalid Aadhaar UID format: ${aadhaarData.uid}")
            
            // Still return the data but mark as invalid
            val errorResponse = """
                {
                    "type": "aadhaar",
                    "uid": "${aadhaarData.uid}",
                    "name": "${aadhaarData.name}",
                    "error": "Invalid Aadhaar UID format",
                    "isValid": false,
                    "rawData": "${rawValue.take(500)}..."
                }
            """.trimIndent()
            
            Log.d("QrScanner", "Sending error response for invalid UID")
            onQrCodeScanned(errorResponse)
        }
    } else {
        Log.w("QrScanner", "❌ Failed to parse Aadhaar data")
        
        // Return raw value with indication it might be Aadhaar
        val fallbackResponse = """
            {
                "type": "aadhaar_raw",
                "error": "Failed to parse Aadhaar data",
                "isValid": false,
                "rawData": "$rawValue"
            }
        """.trimIndent()
        
        Log.d("QrScanner", "Sending fallback response with raw data")
        onQrCodeScanned(fallbackResponse)
    }
}

private fun formatTime(seconds: Int): String {
    val minutes = seconds / 60
    val remainingSeconds = seconds % 60
    return String.format("%02d:%02d", minutes, remainingSeconds)
} 