package com.bureau.qrscanner.sdk.utils

import android.util.Base64
import android.util.Log
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory
import java.io.StringReader
import java.nio.charset.StandardCharsets

/**
 * Utility class for parsing Aadhaar QR code data
 * Aadhaar QR codes contain XML data with personal information
 */
object AadhaarQrParser {
    
    private const val TAG = "AadhaarQrParser"
    
    data class AadhaarData(
        val uid: String = "",
        val name: String = "",
        val gender: String = "",
        val dob: String = "",
        val address: String = "",
        val mobile: String = "",
        val email: String = "",
        val pincode: String = "",
        val state: String = "",
        val district: String = "",
        val subDistrict: String = "",
        val vtc: String = "" // Village/Town/City
    )
    
    /**
     * Parse Aadhaar QR code data from raw QR string
     * @param qrData Raw QR code data
     * @return AadhaarData object with parsed information
     */
    fun parseAadhaarQr(qrData: String): AadhaarData? {
        Log.d(TAG, "Parsing QR data: ${qrData.take(100)}...") // Log first 100 chars
        
        return try {
            if (!isAadhaarQr(qrData)) {
                Log.w(TAG, "Not an Aadhaar QR code")
                return null
            }
            
            // Try different parsing approaches
            val aadhaarData = parseXmlFormat(qrData) 
                ?: parseBase64Format(qrData)
                ?: parseDelimitedFormat(qrData)
            
            if (aadhaarData != null) {
                Log.d(TAG, "Successfully parsed Aadhaar data: ${aadhaarData.name}, UID: ${maskAadhaarUid(aadhaarData.uid)}")
            } else {
                Log.w(TAG, "Failed to parse Aadhaar data")
            }
            
            aadhaarData
        } catch (e: Exception) {
            Log.e(TAG, "Error parsing Aadhaar QR: ${e.message}", e)
            null
        }
    }
    
    /**
     * Parse XML format Aadhaar QR
     */
    private fun parseXmlFormat(qrData: String): AadhaarData? {
        return try {
            val factory = XmlPullParserFactory.newInstance()
            factory.isNamespaceAware = true
            val parser = factory.newPullParser()
            parser.setInput(StringReader(qrData))
            
            var aadhaarData = AadhaarData()
            var eventType = parser.eventType
            
            while (eventType != XmlPullParser.END_DOCUMENT) {
                when (eventType) {
                    XmlPullParser.START_TAG -> {
                        when (parser.name) {
                            "PrintLetterBarcodeData" -> {
                                // Parse attributes from the main tag
                                for (i in 0 until parser.attributeCount) {
                                    when (parser.getAttributeName(i)) {
                                        "uid" -> aadhaarData = aadhaarData.copy(uid = parser.getAttributeValue(i))
                                        "name" -> aadhaarData = aadhaarData.copy(name = parser.getAttributeValue(i))
                                        "gender" -> aadhaarData = aadhaarData.copy(gender = parser.getAttributeValue(i))
                                        "dob" -> aadhaarData = aadhaarData.copy(dob = parser.getAttributeValue(i))
                                        "co" -> aadhaarData = aadhaarData.copy(address = parser.getAttributeValue(i))
                                        "house" -> aadhaarData = aadhaarData.copy(address = parser.getAttributeValue(i))
                                        "loc" -> aadhaarData = aadhaarData.copy(address = aadhaarData.address + " " + parser.getAttributeValue(i))
                                        "vtc" -> aadhaarData = aadhaarData.copy(vtc = parser.getAttributeValue(i))
                                        "subdist" -> aadhaarData = aadhaarData.copy(subDistrict = parser.getAttributeValue(i))
                                        "dist" -> aadhaarData = aadhaarData.copy(district = parser.getAttributeValue(i))
                                        "state" -> aadhaarData = aadhaarData.copy(state = parser.getAttributeValue(i))
                                        "pc" -> aadhaarData = aadhaarData.copy(pincode = parser.getAttributeValue(i))
                                        "mobno" -> aadhaarData = aadhaarData.copy(mobile = parser.getAttributeValue(i))
                                        "email" -> aadhaarData = aadhaarData.copy(email = parser.getAttributeValue(i))
                                    }
                                }
                            }
                        }
                    }
                }
                eventType = parser.next()
            }
            
            if (aadhaarData.uid.isNotEmpty()) aadhaarData else null
        } catch (e: Exception) {
            Log.w(TAG, "XML parsing failed: ${e.message}")
            null
        }
    }
    
    /**
     * Parse Base64 encoded Aadhaar QR (common format)
     */
    private fun parseBase64Format(qrData: String): AadhaarData? {
        return try {
            // Try to decode as base64
            val decodedBytes = Base64.decode(qrData, Base64.DEFAULT)
            val decodedString = String(decodedBytes, StandardCharsets.UTF_8)
            Log.d(TAG, "Base64 decoded: ${decodedString.take(200)}...")
            
            // Try parsing the decoded string as XML
            parseXmlFormat(decodedString)
        } catch (e: Exception) {
            Log.w(TAG, "Base64 parsing failed: ${e.message}")
            null
        }
    }
    
    /**
     * Parse delimited format Aadhaar QR (pipe or comma separated)
     */
    private fun parseDelimitedFormat(qrData: String): AadhaarData? {
        return try {
            // Some Aadhaar QR codes use pipe (|) or comma (,) separation
            val parts = if (qrData.contains("|")) {
                qrData.split("|")
            } else if (qrData.contains(",")) {
                qrData.split(",")
            } else {
                return null
            }
            
            Log.d(TAG, "Delimited format parts: ${parts.size}")
            
            if (parts.size >= 4) {
                AadhaarData(
                    uid = parts.getOrNull(0) ?: "",
                    name = parts.getOrNull(1) ?: "",
                    gender = parts.getOrNull(2) ?: "",
                    dob = parts.getOrNull(3) ?: "",
                    address = parts.getOrNull(4) ?: "",
                    mobile = parts.getOrNull(5) ?: "",
                    email = parts.getOrNull(6) ?: ""
                )
            } else null
        } catch (e: Exception) {
            Log.w(TAG, "Delimited parsing failed: ${e.message}")
            null
        }
    }
    
    /**
     * Check if the QR code data is from an Aadhaar card
     * @param qrData Raw QR code data
     * @return true if it's Aadhaar QR code
     */
    fun isAadhaarQr(qrData: String): Boolean {
        val indicators = listOf(
            "PrintLetterBarcodeData",
            "uid=",
            "name=",
            "gender=",
            "dob=",
            "aadhaar",
            "UIDAI",
            "Permanent Account Number Card" // This appears in some Aadhaar QRs
        )
        
        val lowerData = qrData.lowercase()
        val isAadhaar = indicators.any { lowerData.contains(it.lowercase()) }
        
        // Also check for 12-digit sequences that might be Aadhaar UIDs
        val hasUidPattern = Regex("\\d{12}").find(qrData) != null
        
        // Check if it's base64 encoded Aadhaar data
        val isBase64Aadhaar = try {
            val decoded = Base64.decode(qrData, Base64.DEFAULT)
            val decodedString = String(decoded, StandardCharsets.UTF_8)
            indicators.any { decodedString.lowercase().contains(it.lowercase()) }
        } catch (e: Exception) {
            false
        }
        
        Log.d(TAG, "isAadhaarQr: $isAadhaar, hasUidPattern: $hasUidPattern, isBase64Aadhaar: $isBase64Aadhaar")
        return isAadhaar || hasUidPattern || isBase64Aadhaar
    }
    
    /**
     * Mask Aadhaar UID for privacy (show only last 4 digits)
     * @param uid Full Aadhaar UID
     * @return Masked UID (XXXX XXXX 1234)
     */
    fun maskAadhaarUid(uid: String): String {
        return if (uid.length >= 4) {
            val cleaned = uid.replace(" ", "").replace("-", "")
            val masked = "X".repeat(maxOf(0, cleaned.length - 4)) + cleaned.takeLast(4)
            // Format with spaces: XXXX XXXX 1234
            masked.chunked(4).joinToString(" ")
        } else uid
    }
    
    /**
     * Validate Aadhaar UID format
     * @param uid Aadhaar UID to validate
     * @return true if valid format
     */
    fun isValidAadhaarUid(uid: String): Boolean {
        val cleanedUid = uid.replace(" ", "").replace("-", "")
        return cleanedUid.length == 12 && cleanedUid.all { it.isDigit() }
    }
    
    /**
     * Format address from Aadhaar components
     */
    fun formatAddress(aadhaarData: AadhaarData): String {
        val components = listOf(
            aadhaarData.address,
            aadhaarData.vtc,
            aadhaarData.subDistrict,
            aadhaarData.district,
            aadhaarData.state,
            aadhaarData.pincode
        ).filter { it.isNotEmpty() }
        
        return components.joinToString(", ")
    }
} 