package com.bureau.qrscanner.core.network.repository

import com.bureau.qrscanner.core.network.BureauApi
import com.bureau.qrscanner.core.network.model.AadhaarData
import com.bureau.qrscanner.core.network.model.AadhaarQrRequest
import com.bureau.qrscanner.core.network.model.ErrorResponse
import com.squareup.moshi.Moshi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class QrRepositoryImpl @Inject constructor(
    private val bureauApi: BureauApi,
    private val moshi: Moshi
) : QrRepository {

    override suspend fun submitAadhaarQr(
        qrData: String,
        consent: Boolean,
        consentText: String
    ): QrSubmissionResult {
        return try {
            val request = AadhaarQrRequest(
                encodedString = qrData,
                consent = consent,
                consentText = consentText,
                custID = "435", // Default custID for testing
                sessionId = System.currentTimeMillis().toString()
            )
            
            val response = bureauApi.submitAadhaarQr(request)
            
            if (response.isSuccessful) {
                val body = response.body()
                if (body?.statusCode == 200 && body.name != null) {
                    // Convert response to legacy AadhaarData format
                    val legacyData = AadhaarData(
                        name = body.name,
                        fatherName = body.careOf,
                        dob = body.dob,
                        gender = body.gender,
                        address = "${body.house ?: ""} ${body.street ?: ""} ${body.location ?: ""} ${body.district ?: ""} ${body.state ?: ""} ${body.pincode ?: ""}".trim(),
                        aadhaarNumber = body.aadhaarLast4Digit,
                        panNumber = null,
                        voterNumber = null
                    )
                    QrSubmissionResult.Success(legacyData)
                } else {
                    QrSubmissionResult.Error(
                        message = "Failed to process QR code",
                        statusCode = body?.statusCode ?: response.code()
                    )
                }
            } else {
                val errorBody = response.errorBody()?.string()
                val errorResponse = parseErrorResponse(errorBody)
                QrSubmissionResult.Error(
                    message = errorResponse?.error ?: errorResponse?.errorMessage ?: errorResponse?.message ?: "API request failed",
                    statusCode = response.code()
                )
            }
        } catch (e: Exception) {
            QrSubmissionResult.NetworkError("Network error: ${e.message}")
        }
    }

    override suspend fun submitPanQr(
        qrData: String,
        consent: Boolean,
        consentText: String
    ): QrSubmissionResult {
        return try {
            val request = AadhaarQrRequest(
                encodedString = qrData,
                consent = consent,
                consentText = consentText,
                custID = "435", // Default custID for testing
                sessionId = System.currentTimeMillis().toString()
            )
            
            val response = bureauApi.submitPanQr(request)
            
            if (response.isSuccessful) {
                val body = response.body()
                if (body?.statusCode == 200 && body.name != null) {
                    // Convert response to legacy AadhaarData format
                    val legacyData = AadhaarData(
                        name = body.name,
                        fatherName = body.careOf,
                        dob = body.dob,
                        gender = body.gender,
                        address = "${body.house ?: ""} ${body.street ?: ""} ${body.location ?: ""} ${body.district ?: ""} ${body.state ?: ""} ${body.pincode ?: ""}".trim(),
                        aadhaarNumber = body.aadhaarLast4Digit,
                        panNumber = null,
                        voterNumber = null
                    )
                    QrSubmissionResult.Success(legacyData)
                } else {
                    QrSubmissionResult.Error(
                        message = "Failed to process QR code",
                        statusCode = body?.statusCode ?: response.code()
                    )
                }
            } else {
                val errorBody = response.errorBody()?.string()
                val errorResponse = parseErrorResponse(errorBody)
                QrSubmissionResult.Error(
                    message = errorResponse?.error ?: errorResponse?.errorMessage ?: errorResponse?.message ?: "API request failed",
                    statusCode = response.code()
                )
            }
        } catch (e: Exception) {
            QrSubmissionResult.NetworkError("Network error: ${e.message}")
        }
    }

    override suspend fun submitVoterQr(
        qrData: String,
        consent: Boolean,
        consentText: String
    ): QrSubmissionResult {
        return try {
            val request = AadhaarQrRequest(
                encodedString = qrData,
                consent = consent,
                consentText = consentText,
                custID = "435", // Default custID for testing
                sessionId = System.currentTimeMillis().toString()
            )
            
            val response = bureauApi.submitVoterQr(request)
            
            if (response.isSuccessful) {
                val body = response.body()
                if (body?.statusCode == 200 && body.name != null) {
                    // Convert response to legacy AadhaarData format
                    val legacyData = AadhaarData(
                        name = body.name,
                        fatherName = body.careOf,
                        dob = body.dob,
                        gender = body.gender,
                        address = "${body.house ?: ""} ${body.street ?: ""} ${body.location ?: ""} ${body.district ?: ""} ${body.state ?: ""} ${body.pincode ?: ""}".trim(),
                        aadhaarNumber = body.aadhaarLast4Digit,
                        panNumber = null,
                        voterNumber = null
                    )
                    QrSubmissionResult.Success(legacyData)
                } else {
                    QrSubmissionResult.Error(
                        message = "Failed to process QR code",
                        statusCode = body?.statusCode ?: response.code()
                    )
                }
            } else {
                val errorBody = response.errorBody()?.string()
                val errorResponse = parseErrorResponse(errorBody)
                QrSubmissionResult.Error(
                    message = errorResponse?.error ?: errorResponse?.errorMessage ?: errorResponse?.message ?: "API request failed",
                    statusCode = response.code()
                )
            }
        } catch (e: Exception) {
            QrSubmissionResult.NetworkError("Network error: ${e.message}")
        }
    }

    private fun parseErrorResponse(errorBody: String?): ErrorResponse? {
        return try {
            if (errorBody != null) {
                val adapter = moshi.adapter(ErrorResponse::class.java)
                adapter.fromJson(errorBody)
            } else {
                null
            }
        } catch (e: Exception) {
            null
        }
    }


} 