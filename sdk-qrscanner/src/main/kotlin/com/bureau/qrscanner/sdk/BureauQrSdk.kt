package com.bureau.qrscanner.sdk

import android.app.Activity
import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.ColorInt
import androidx.annotation.FontRes
import kotlinx.parcelize.Parcelize
import com.bureau.qrscanner.core.network.model.AadhaarData

/**
 * Main SDK interface for Bureau QR Scanner
 */
interface BureauQrSdk {
    /**
     * Start QR scanning flow
     * @param context Activity context
     * @param config Branding configuration
     * @param scannerConfig Scanner behavior configuration
     * @param callback Result callback
     */
    fun start(
        context: Activity,
        config: BrandingConfig,
        scannerConfig: ScannerConfig = ScannerConfig(),
        callback: BureauQrCallback
    )
}

/**
 * Branding configuration for white-label customization
 */
data class BrandingConfig(
    @ColorInt val primaryColor: Int,
    @ColorInt val secondaryColor: Int,
    @FontRes val fontRes: Int? = null,
    val logoUrl: String? = null,
    val organizationName: String = "Bureau"
) : Parcelable {
    
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readInt(),
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        parcel.readString() ?: "Bureau"
    )
    
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(primaryColor)
        parcel.writeInt(secondaryColor)
        parcel.writeValue(fontRes)
        parcel.writeString(logoUrl)
        parcel.writeString(organizationName)
    }
    
    override fun describeContents(): Int = 0
    
    companion object CREATOR : Parcelable.Creator<BrandingConfig> {
        override fun createFromParcel(parcel: Parcel): BrandingConfig {
            return BrandingConfig(parcel)
        }
        
        override fun newArray(size: Int): Array<BrandingConfig?> {
            return arrayOfNulls(size)
        }
    }
}

/**
 * Scanner behavior configuration
 */
@Parcelize
data class ScannerConfig(
    val timeoutSeconds: Int = 130,
    val showFirstTimeHelp: Boolean = true,
    val showHelpCarousel: Boolean = true,
    val enableFlashToggle: Boolean = true,
    val enableAutoZoom: Boolean = true,
    val enableAutoFocus: Boolean = true,
    val enableNudges: Boolean = true,
    val frameAnimationEnabled: Boolean = true
) : Parcelable

/**
 * Document types supported by the scanner
 */
enum class DocumentType(val displayName: String, val apiEndpoint: String) {
    AADHAAR("Aadhaar ID", "aadhaar-qr"),
    PAN("PAN", "pan-qr"),
    VOTER_ID("Voter ID", "voter-qr")
}

/**
 * Callback interface for SDK events
 */
interface BureauQrCallback {
    fun onSuccess(data: AadhaarData)
    fun onFailure(error: BureauQrError)
    fun onCancelled()
    fun onTimeout() = Unit // Optional timeout callback
}

/**
 * Error types that can occur during QR scanning
 */
sealed class BureauQrError(val message: String) {
    object CameraPermissionDenied : BureauQrError("Camera permission denied")
    data class NetworkError(val errorMessage: String) : BureauQrError(errorMessage)
    data class ApiError(val errorMessage: String) : BureauQrError(errorMessage)
    object ScanTimeout : BureauQrError("QR scan timed out")
    object InvalidQrCode : BureauQrError("Invalid QR code format")
    data class Unknown(val throwable: Throwable) : BureauQrError("Unknown error: ${throwable.message}")
} 