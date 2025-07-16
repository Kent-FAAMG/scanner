package com.bureau.qrscanner.sdk.viewmodel

import app.cash.turbine.test
import com.bureau.qrscanner.core.network.BureauApi
import com.bureau.qrscanner.core.network.model.AadhaarData
import com.bureau.qrscanner.core.network.model.AadhaarQrRequest
import com.bureau.qrscanner.core.network.model.AadhaarQrResponse
import com.bureau.qrscanner.sdk.BureauQrError
import com.bureau.qrscanner.sdk.DocumentType
import com.bureau.qrscanner.sdk.ui.viewmodel.QrScannerScreen
import com.bureau.qrscanner.sdk.ui.viewmodel.QrScannerViewModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Test
import retrofit2.Response

@OptIn(ExperimentalCoroutinesApi::class)
class QrScannerViewModelTest {

    private val mockBureauApi = mockk<BureauApi>()
    private lateinit var viewModel: QrScannerViewModel
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        viewModel = QrScannerViewModel(mockBureauApi)
    }

    @Test
    fun `selectDocumentType should update state and navigate to scanner`() = runTest {
        viewModel.uiState.test {
            // Initial state
            val initialState = awaitItem()
            assert(initialState.currentScreen == QrScannerScreen.DocTypeSelection)
            assert(initialState.selectedDocumentType == null)

            // Select document type
            viewModel.selectDocumentType(DocumentType.AADHAAR)

            // Verify state update
            val updatedState = awaitItem()
            assert(updatedState.currentScreen == QrScannerScreen.Scanner)
            assert(updatedState.selectedDocumentType == DocumentType.AADHAAR)
        }
    }

    @Test
    fun `onQrCodeScanned should submit data and show success on valid response`() = runTest {
        // Setup
        val testQrData = "test-qr-data"
        val successResponse = AadhaarQrResponse(
            success = true,
            message = "Success",
            data = AadhaarData(
                name = "Test User",
                fatherName = "Test Father",
                dob = "01/01/1990",
                gender = "M",
                address = "Test Address",
                aadhaarNumber = "1234567890",
                panNumber = null,
                voterNumber = null
            )
        )

        coEvery { 
            mockBureauApi.submitAadhaarQr(any()) 
        } returns Response.success(successResponse)

        // Select document type first
        viewModel.selectDocumentType(DocumentType.AADHAAR)

        viewModel.uiState.test {
            // Skip initial states
            skipItems(1) // DocTypeSelection -> Scanner

            // Scan QR code
            viewModel.onQrCodeScanned(testQrData)

            // Should show loading
            val loadingState = awaitItem()
            assert(loadingState.currentScreen == QrScannerScreen.Loading)
            assert(loadingState.scannedQrData == testQrData)

            // Should show success
            val successState = awaitItem()
            assert(successState.currentScreen == QrScannerScreen.Success)
            assert(successState.successData?.name == "Test User")
            assert(successState.statusTitle.contains("Aadhaar"))
        }
    }

    @Test
    fun `onQrCodeScanned should show error on API failure`() = runTest {
        // Setup
        val testQrData = "test-qr-data"
        val errorResponse = AadhaarQrResponse(
            success = false,
            message = "Invalid QR code",
            data = null
        )

        coEvery { 
            mockBureauApi.submitAadhaarQr(any()) 
        } returns Response.success(errorResponse)

        // Select document type first
        viewModel.selectDocumentType(DocumentType.AADHAAR)

        viewModel.uiState.test {
            // Skip initial states
            skipItems(1) // DocTypeSelection -> Scanner

            // Scan QR code
            viewModel.onQrCodeScanned(testQrData)

            // Should show loading
            val loadingState = awaitItem()
            assert(loadingState.currentScreen == QrScannerScreen.Loading)

            // Should show error
            val errorState = awaitItem()
            assert(errorState.currentScreen == QrScannerScreen.Error)
            assert(errorState.error is BureauQrError.ApiError)
            assert(errorState.statusTitle == "Error Message!")
        }
    }

    @Test
    fun `onScanTimeout should show timeout error`() = runTest {
        viewModel.uiState.test {
            // Skip initial state
            skipItems(1)

            // Trigger timeout
            viewModel.onScanTimeout()

            // Should show error with timeout
            val errorState = awaitItem()
            assert(errorState.currentScreen == QrScannerScreen.Error)
            assert(errorState.error is BureauQrError.ScanTimeout)
        }
    }

    @Test
    fun `onRetakePressed should return to scanner screen`() = runTest {
        // First trigger an error
        viewModel.onScanTimeout()

        viewModel.uiState.test {
            // Skip to error state
            skipItems(1)

            // Retake
            viewModel.onRetakePressed()

            // Should return to scanner
            val scannerState = awaitItem()
            assert(scannerState.currentScreen == QrScannerScreen.Scanner)
            assert(scannerState.error == null)
            assert(scannerState.scannedQrData == null)
        }
    }

    @Test
    fun `onBackPressed should handle navigation correctly`() = runTest {
        viewModel.uiState.test {
            // Initial state - back should exit
            val initialState = awaitItem()
            assert(initialState.currentScreen == QrScannerScreen.DocTypeSelection)

            viewModel.onBackPressed()

            val exitState = awaitItem()
            assert(exitState.shouldExit)
        }
    }
} 