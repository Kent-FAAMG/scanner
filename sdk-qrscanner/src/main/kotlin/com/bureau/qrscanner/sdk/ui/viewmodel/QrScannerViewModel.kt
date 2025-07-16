package com.bureau.qrscanner.sdk.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bureau.qrscanner.core.network.model.AadhaarData
import com.bureau.qrscanner.core.network.repository.QrRepository
import com.bureau.qrscanner.core.network.repository.QrSubmissionResult
import com.bureau.qrscanner.sdk.BureauQrError
import com.bureau.qrscanner.sdk.DocumentType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QrScannerViewModel @Inject constructor(
    private val qrRepository: QrRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(QrScannerUiState())
    val uiState: StateFlow<QrScannerUiState> = _uiState.asStateFlow()

    fun selectDocumentType(documentType: DocumentType) {
        _uiState.value = _uiState.value.copy(
            selectedDocumentType = documentType,
            currentScreen = QrScannerScreen.Scanner
        )
    }

    fun onQrCodeScanned(qrData: String) {
        if (_uiState.value.currentScreen != QrScannerScreen.Scanner || _uiState.value.isSubmitting) return
        
        _uiState.value = _uiState.value.copy(
            currentScreen = QrScannerScreen.Loading,
            scannedQrData = qrData,
            isSubmitting = true
        )
        
        submitQrData(qrData)
    }

    private fun submitQrData(qrData: String) {
        viewModelScope.launch {
            val result = when (_uiState.value.selectedDocumentType) {
                DocumentType.AADHAAR -> qrRepository.submitAadhaarQr(qrData)
                DocumentType.PAN -> qrRepository.submitPanQr(qrData)
                DocumentType.VOTER_ID -> qrRepository.submitVoterQr(qrData)
                null -> {
                    showError(BureauQrError.Unknown(IllegalStateException("No document type selected")))
                    return@launch
                }
            }

            when (result) {
                is QrSubmissionResult.Success -> {
                    val docType = _uiState.value.selectedDocumentType?.displayName
                    _uiState.value = _uiState.value.copy(
                        currentScreen = QrScannerScreen.Success,
                        successData = result.data,
                        statusTitle = "$docType QR Verified Successfully!",
                        statusMessage = "Your document has been processed successfully.",
                        isSubmitting = false
                    )
                }
                is QrSubmissionResult.Error -> {
                    _uiState.value = _uiState.value.copy(isSubmitting = false)
                    showError(BureauQrError.ApiError(result.message))
                }
                is QrSubmissionResult.NetworkError -> {
                    _uiState.value = _uiState.value.copy(isSubmitting = false)
                    showError(BureauQrError.NetworkError(result.message))
                }
            }
        }
    }

    private fun showError(error: BureauQrError) {
        val (title, message) = when (error) {
            is BureauQrError.NetworkError -> {
                "Network Error" to "Please check your internet connection and try again."
            }
            is BureauQrError.ApiError -> {
                val fallbackMessage = "Unable to verify your document. Please try again."
                "Verification Failed" to (error.message.takeIf { it.isNotBlank() } ?: fallbackMessage)
            }
            is BureauQrError.ScanTimeout -> {
                "Scan Timeout" to "Please try scanning the QR code again."
            }
            is BureauQrError.InvalidQrCode -> {
                "Invalid QR Code" to "Please scan a valid government ID QR code."
            }
            is BureauQrError.CameraPermissionDenied -> {
                "Camera Permission Required" to "Please grant camera permission to scan QR codes."
            }
            is BureauQrError.Unknown -> {
                "Something went wrong" to "An unexpected error occurred. Please try again."
            }
        }
        
        _uiState.value = _uiState.value.copy(
            currentScreen = QrScannerScreen.Error,
            error = error,
            statusTitle = title,
            statusMessage = message
        )
    }

    fun onScanTimeout() {
        showError(BureauQrError.ScanTimeout)
    }

    fun onRetakePressed() {
        _uiState.value = _uiState.value.copy(
            currentScreen = QrScannerScreen.Scanner,
            error = null,
            scannedQrData = null,
            isSubmitting = false
        )
    }

    fun onBackPressed() {
        when (_uiState.value.currentScreen) {
            QrScannerScreen.DocTypeSelection -> {
                // Handle exit from SDK
                _uiState.value = _uiState.value.copy(shouldExit = true)
            }
            QrScannerScreen.Scanner -> {
                _uiState.value = _uiState.value.copy(
                    currentScreen = QrScannerScreen.DocTypeSelection,
                    selectedDocumentType = null
                )
            }
            else -> {
                // Other screens can't go back
            }
        }
    }

    fun onSuccessRedirect() {
        _uiState.value = _uiState.value.copy(shouldExitWithSuccess = true)
    }

    fun onErrorRedirect() {
        _uiState.value = _uiState.value.copy(shouldExitWithError = true)
    }

    fun markExitHandled() {
        _uiState.value = _uiState.value.copy(
            shouldExit = false,
            shouldExitWithSuccess = false,
            shouldExitWithError = false
        )
    }
}

data class QrScannerUiState(
    val currentScreen: QrScannerScreen = QrScannerScreen.DocTypeSelection,
    val selectedDocumentType: DocumentType? = null,
    val scannedQrData: String? = null,
    val successData: AadhaarData? = null,
    val error: BureauQrError? = null,
    val statusTitle: String = "",
    val statusMessage: String = "",
    val shouldExit: Boolean = false,
    val shouldExitWithSuccess: Boolean = false,
    val shouldExitWithError: Boolean = false,
    val isSubmitting: Boolean = false
)

enum class QrScannerScreen {
    DocTypeSelection,
    Scanner,
    Loading,
    Success,
    Error
} 