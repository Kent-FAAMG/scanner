package com.bureau.qrscanner.core.network.repository

import com.bureau.qrscanner.core.network.model.AadhaarData
import com.bureau.qrscanner.core.network.model.ErrorResponse

sealed class QrSubmissionResult {
    data class Success(val data: AadhaarData) : QrSubmissionResult()
    data class Error(val message: String, val statusCode: Int? = null) : QrSubmissionResult()
    data class NetworkError(val message: String) : QrSubmissionResult()
}

interface QrRepository {
    suspend fun submitAadhaarQr(
        qrData: String,
        consent: Boolean = true,
        consentText: String = "I approve BureauID to capture and process user data based on user consent"
    ): QrSubmissionResult
    
    suspend fun submitPanQr(
        qrData: String,
        consent: Boolean = true,
        consentText: String = "I approve BureauID to capture and process user data based on user consent"
    ): QrSubmissionResult
    
    suspend fun submitVoterQr(
        qrData: String,
        consent: Boolean = true,
        consentText: String = "I approve BureauID to capture and process user data based on user consent"
    ): QrSubmissionResult
} 