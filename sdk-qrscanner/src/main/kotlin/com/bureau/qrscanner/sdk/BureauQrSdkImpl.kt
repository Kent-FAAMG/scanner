package com.bureau.qrscanner.sdk

import android.app.Activity
import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.bureau.qrscanner.sdk.ui.QrScannerFlow
import dagger.hilt.android.AndroidEntryPoint

class BureauQrSdkImpl : BureauQrSdk {
    
    override fun start(
        context: Activity,
        config: BrandingConfig,
        scannerConfig: ScannerConfig,
        callback: BureauQrCallback
    ) {
        val intent = Intent(context, QrScannerActivity::class.java).apply {
            putExtra(EXTRA_BRANDING_CONFIG, config)
            putExtra(EXTRA_SCANNER_CONFIG, scannerConfig)
        }
        
        // Store callback globally for activity to access
        QrScannerActivity.callback = callback
        
        context.startActivity(intent)
    }
    
    companion object {
        const val EXTRA_BRANDING_CONFIG = "extra_branding_config"
        const val EXTRA_SCANNER_CONFIG = "extra_scanner_config"
    }
}

@AndroidEntryPoint
class QrScannerActivity : ComponentActivity() {
    
    companion object {
        var callback: BureauQrCallback? = null
    }
    
    override fun onCreate(savedInstanceState: android.os.Bundle?) {
        super.onCreate(savedInstanceState)
        
        val brandingConfig = if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(BureauQrSdkImpl.EXTRA_BRANDING_CONFIG, BrandingConfig::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(BureauQrSdkImpl.EXTRA_BRANDING_CONFIG)
        } ?: BrandingConfig(
            primaryColor = 0xFF0066CC.toInt(),
            secondaryColor = 0xFF00CC66.toInt()
        )
        
        val scannerConfig = if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(BureauQrSdkImpl.EXTRA_SCANNER_CONFIG, ScannerConfig::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(BureauQrSdkImpl.EXTRA_SCANNER_CONFIG)
        } ?: ScannerConfig()
        
        val qrCallback = callback ?: object : BureauQrCallback {
            override fun onSuccess(data: com.bureau.qrscanner.core.network.model.AadhaarData) {
                finish()
            }
            
            override fun onFailure(error: BureauQrError) {
                finish()
            }
            
            override fun onCancelled() {
                finish()
            }
        }
        
        setContent {
            QrScannerFlow(
                brandingConfig = brandingConfig,
                scannerConfig = scannerConfig,
                callback = object : BureauQrCallback {
                    override fun onSuccess(data: com.bureau.qrscanner.core.network.model.AadhaarData) {
                        qrCallback.onSuccess(data)
                        finish()
                    }
                    
                    override fun onFailure(error: BureauQrError) {
                        qrCallback.onFailure(error)
                        finish()
                    }
                    
                    override fun onCancelled() {
                        qrCallback.onCancelled()
                        finish()
                    }
                }
            )
        }
    }
    
    override fun onDestroy() {
        super.onDestroy()
        callback = null
    }
} 