package com.bureau.qrscanner.core.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ErrorResponse(
    @Json(name = "success")
    val success: Boolean = false,
    
    @Json(name = "message")
    val message: String?,
    
    @Json(name = "statusCode")
    val statusCode: Int?,
    
    @Json(name = "error")
    val error: String?,
    
    @Json(name = "errorMessage")
    val errorMessage: String?
) 