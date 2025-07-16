package com.bureau.qr.host

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bureau.qrscanner.core.network.model.AadhaarData
import com.bureau.qrscanner.core.ui.theme.BureauTheme
import com.bureau.qrscanner.sdk.BrandingConfig
import com.bureau.qrscanner.sdk.BureauQrCallback
import com.bureau.qrscanner.sdk.BureauQrError
import com.bureau.qrscanner.sdk.BureauQrSdkImpl
import com.bureau.qrscanner.sdk.ScannerConfig
import com.chuckerteam.chucker.api.Chucker
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val bureauQrSdk: BureauQrSdkImpl = BureauQrSdkImpl()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BureauTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen()
                }
            }
        }
    }

    @Composable
    fun MainScreen() {
        var lastResult by remember { mutableStateOf<String?>(null) }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Title
            Text(
                text = "Bureau QR Scanner SDK",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Text(
                text = "Demo Integration App",
                fontSize = 16.sp,
                color = Color.Gray,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 32.dp)
            )

            // Default Theme Button
            Button(
                onClick = { 
                    startQrScanner(
                        config = getDefaultConfig(),
                        scannerConfig = ScannerConfig()
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text("Start QR Scanner (Default)")
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Custom Theme with Advanced Features
            Button(
                onClick = { 
                    startQrScanner(
                        config = getCustomConfig(),
                        scannerConfig = ScannerConfig(
                            timeoutSeconds = 60,
                            enableNudges = true,
                            enableAutoFocus = true,
                            enableAutoZoom = true,
                            showHelpCarousel = true,
                            frameAnimationEnabled = true
                        )
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFE91E63)
                )
            ) {
                Text("Advanced Scanner (1 min timeout)")
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Minimal Scanner
            Button(
                onClick = { 
                    startQrScanner(
                        config = getGreenConfig(),
                        scannerConfig = ScannerConfig(
                            timeoutSeconds = 30,
                            enableNudges = false,
                            enableAutoFocus = false,
                            enableAutoZoom = false,
                            showHelpCarousel = false,
                            frameAnimationEnabled = false
                        )
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF4CAF50)
                )
            ) {
                Text("Minimal Scanner (30s timeout)")
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Chuck HTTP Inspector Button
            Button(
                onClick = { 
                    Chucker.getLaunchIntent(this@MainActivity)?.let { intent ->
                        startActivity(intent)
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF9C27B0)
                )
            ) {
                Text("🔍 Open HTTP Inspector (Chuck)")
            }

            // Result Display
            lastResult?.let { result ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 24.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text(
                            text = "Last Result:",
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                        Text(
                            text = result,
                            fontSize = 14.sp
                        )
                    }
                }
            }
        }
    }

    private fun startQrScanner(config: BrandingConfig, scannerConfig: ScannerConfig = ScannerConfig()) {
        val callback = object : BureauQrCallback {
            override fun onSuccess(data: AadhaarData) {
                Log.d("QR_SDK", "Success: ${data.name}")
                val result = buildString {
                    append("✅ SUCCESS\n")
                    append("Name: ${data.name}\n")
                    when {
                        data.aadhaarNumber != null -> append("Aadhaar: ${data.aadhaarNumber}")
                        data.panNumber != null -> append("PAN: ${data.panNumber}")
                        data.voterNumber != null -> append("Voter ID: ${data.voterNumber}")
                    }
                }
                runOnUiThread {
                    Toast.makeText(this@MainActivity, "QR Scan Successful!", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(error: BureauQrError) {
                Log.e("QR_SDK", "Error: ${error.message}")
                runOnUiThread {
                    Toast.makeText(this@MainActivity, "Error: ${error.message}", Toast.LENGTH_LONG).show()
                }
            }

            override fun onCancelled() {
                Log.d("QR_SDK", "Cancelled")
                runOnUiThread {
                    Toast.makeText(this@MainActivity, "QR Scan Cancelled", Toast.LENGTH_SHORT).show()
                }
            }
        }

        bureauQrSdk.start(this, config, scannerConfig, callback)
    }

    private fun getDefaultConfig() = BrandingConfig(
        primaryColor = 0xFF0066CC.toInt(),
        secondaryColor = 0xFF00CC66.toInt(),
        organizationName = "Bureau"
    )

    private fun getCustomConfig() = BrandingConfig(
        primaryColor = 0xFFE91E63.toInt(),
        secondaryColor = 0xFF9C27B0.toInt(),
        organizationName = "Custom Corp"
    )

    private fun getGreenConfig() = BrandingConfig(
        primaryColor = 0xFF4CAF50.toInt(),
        secondaryColor = 0xFF2E7D32.toInt(),
        organizationName = "Green Company"
    )
} 