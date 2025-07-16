package com.bureau.qrscanner.core.network

import com.bureau.qrscanner.core.network.model.AadhaarQrRequest
import com.bureau.qrscanner.core.network.model.AadhaarQrResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface BureauApi {
    
    @POST("v2/services/aadhaar-qr")
    suspend fun submitAadhaarQr(
        @Body request: AadhaarQrRequest
    ): Response<AadhaarQrResponse>
    
    @POST("v2/services/pan-qr")
    suspend fun submitPanQr(
        @Body request: AadhaarQrRequest
    ): Response<AadhaarQrResponse>
    
    @POST("v2/services/voter-qr")
    suspend fun submitVoterQr(
        @Body request: AadhaarQrRequest
    ): Response<AadhaarQrResponse>
} 