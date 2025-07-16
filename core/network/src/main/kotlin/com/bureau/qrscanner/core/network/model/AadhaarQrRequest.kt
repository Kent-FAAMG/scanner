package com.bureau.qrscanner.core.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AadhaarQrRequest(
    @Json(name = "encodedString")
    val encodedString: String,
    
    @Json(name = "imageBase64")
    val imageBase64: String = "",
    
    @Json(name = "imageUrl")
    val imageUrl: String = "",
    
    @Json(name = "consent")
    val consent: Boolean,
    
    @Json(name = "consentText")
    val consentText: String,
    
    @Json(name = "custID")
    val custID: String,
    
    @Json(name = "sessionId")
    val sessionId: String
) 