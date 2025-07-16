plugins {
    id("bureau.android.library")
    id("bureau.android.hilt")
}

android {
    namespace = "com.bureau.qrscanner.core.network"
}

dependencies {
    api(libs.bundles.networking)
    implementation(libs.androidx.core.ktx)
    
    ksp(libs.moshi.kotlin.codegen)
    
    // Debugging - Chuck HTTP Inspector
    debugImplementation(libs.chucker)
    releaseImplementation(libs.chucker.no.op)
    
    testImplementation(libs.bundles.testing)
} 