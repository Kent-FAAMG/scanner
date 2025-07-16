package com.bureau.qrscanner.sdk.ui

import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.hilt.navigation.compose.hiltViewModel
import com.bureau.qrscanner.core.ui.theme.BureauTheme
import com.bureau.qrscanner.sdk.BrandingConfig
import com.bureau.qrscanner.sdk.BureauQrCallback
import com.bureau.qrscanner.sdk.ScannerConfig
import com.bureau.qrscanner.sdk.ui.screens.*
import com.bureau.qrscanner.sdk.ui.viewmodel.QrScannerScreen
import com.bureau.qrscanner.sdk.ui.viewmodel.QrScannerViewModel

@Composable
fun QrScannerFlow(
    brandingConfig: BrandingConfig,
    scannerConfig: ScannerConfig,
    callback: BureauQrCallback,
    viewModel: QrScannerViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val primaryColor = Color(brandingConfig.primaryColor)
    val secondaryColor = Color(brandingConfig.secondaryColor)

    // Handle SDK exit events
    LaunchedEffect(uiState.shouldExit, uiState.shouldExitWithSuccess, uiState.shouldExitWithError) {
        when {
            uiState.shouldExit -> {
                callback.onCancelled()
                viewModel.markExitHandled()
            }
            uiState.shouldExitWithSuccess -> {
                uiState.successData?.let { data ->
                    callback.onSuccess(data)
                } ?: callback.onCancelled()
                viewModel.markExitHandled()
            }
            uiState.shouldExitWithError -> {
                uiState.error?.let { error ->
                    callback.onFailure(error)
                } ?: callback.onCancelled()
                viewModel.markExitHandled()
            }
        }
    }

    BureauTheme(
        primaryColor = primaryColor,
        secondaryColor = secondaryColor
    ) {
        when (uiState.currentScreen) {
            QrScannerScreen.DocTypeSelection -> {
                DocTypeScreen(
                    primaryColor = primaryColor,
                    onDocumentTypeSelected = viewModel::selectDocumentType,
                    onBackPressed = viewModel::onBackPressed
                )
            }

            QrScannerScreen.Scanner -> {
                PixelPerfectScannerScreen(
                    primaryColor = primaryColor,
                    scannerConfig = scannerConfig,
                    onQrCodeScanned = viewModel::onQrCodeScanned,
                    onClosePressed = viewModel::onBackPressed,
                    onTimeout = viewModel::onScanTimeout
                )
            }

            QrScannerScreen.Loading -> {
                LoaderScreen(
                    primaryColor = primaryColor
                )
            }

            QrScannerScreen.Success -> {
                StatusScreen(
                    isSuccess = true,
                    title = uiState.statusTitle,
                    message = uiState.statusMessage,
                    primaryColor = primaryColor,
                    onAutoRedirect = viewModel::onSuccessRedirect
                )
            }

            QrScannerScreen.Error -> {
                StatusScreen(
                    isSuccess = false,
                    title = uiState.statusTitle,
                    message = uiState.statusMessage,
                    primaryColor = primaryColor,
                    onRetakePressed = viewModel::onRetakePressed,
                    onAutoRedirect = viewModel::onErrorRedirect
                )
            }
        }
    }
} 