package com.bureau.qrscanner.core.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AadhaarQrResponse(
    @Json(name = "aadhaarLast4Digit")
    val aadhaarLast4Digit: String?,
    
    @Json(name = "aadhaarLastDigit")
    val aadhaarLastDigit: String?,
    
    @Json(name = "bureauConsentId")
    val bureauConsentId: String?,
    
    @Json(name = "careOf")
    val careOf: String?,
    
    @Json(name = "consent")
    val consent: Boolean?,
    
    @Json(name = "custID")
    val custID: String?,
    
    @Json(name = "district")
    val district: String?,
    
    @Json(name = "dob")
    val dob: String?,
    
    @Json(name = "emailMobileStatus")
    val emailMobileStatus: String?,
    
    @Json(name = "emailRegistered")
    val emailRegistered: Boolean?,
    
    @Json(name = "emailSha256")
    val emailSha256: String?,
    
    @Json(name = "gender")
    val gender: String?,
    
    @Json(name = "house")
    val house: String?,
    
    @Json(name = "imageBase64")
    val imageBase64: String?,
    
    @Json(name = "landmark")
    val landmark: String?,
    
    @Json(name = "last4DigitsMobile")
    val last4DigitsMobile: String?,
    
    @Json(name = "location")
    val location: String?,
    
    @Json(name = "merchantId")
    val merchantId: String?,
    
    @Json(name = "mobileRegistered")
    val mobileRegistered: Boolean?,
    
    @Json(name = "mobileSha256")
    val mobileSha256: String?,
    
    @Json(name = "name")
    val name: String?,
    
    @Json(name = "pincode")
    val pincode: String?,
    
    @Json(name = "postOffice")
    val postOffice: String?,
    
    @Json(name = "referenceId")
    val referenceId: String?,
    
    @Json(name = "requestId")
    val requestId: String?,
    
    @Json(name = "secureQr")
    val secureQr: Boolean?,
    
    @Json(name = "sessionId")
    val sessionId: String?,
    
    @Json(name = "signatureBase64")
    val signatureBase64: String?,
    
    @Json(name = "signedBase64")
    val signedBase64: String?,
    
    @Json(name = "state")
    val state: String?,
    
    @Json(name = "statusCode")
    val statusCode: Int?,
    
    @Json(name = "street")
    val street: String?,
    
    @Json(name = "subDistrict")
    val subDistrict: String?,
    
    @Json(name = "timestamp")
    val timestamp: Long?,
    
    @Json(name = "version")
    val version: String?,
    
    @Json(name = "vtc")
    val vtc: String?
)

// Legacy data class for backward compatibility
@JsonClass(generateAdapter = true)
data class AadhaarData(
    @Json(name = "name")
    val name: String?,
    
    @Json(name = "fatherName")
    val fatherName: String?,
    
    @Json(name = "dob")
    val dob: String?,
    
    @Json(name = "gender")
    val gender: String?,
    
    @Json(name = "address")
    val address: String?,
    
    @Json(name = "aadhaarNumber")
    val aadhaarNumber: String?,
    
    @Json(name = "panNumber")
    val panNumber: String?,
    
    @Json(name = "voterNumber")
    val voterNumber: String?
) 