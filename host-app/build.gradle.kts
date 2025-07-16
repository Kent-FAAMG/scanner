plugins {
    id("bureau.android.application.compose")
    id("bureau.android.hilt")
}

android {
    namespace = "com.bureau.qr.host"
    
    defaultConfig {
        applicationId = "com.bureau.qr.host"
        versionCode = 1
        versionName = "1.0"
    }
}

dependencies {
    implementation(project(":sdk-qrscanner"))
    
    // Compose & UI
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.bundles.compose)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    
    // Hilt
    implementation(libs.hilt.navigation.compose)
    
    // Debugging - Chuck HTTP Inspector
    debugImplementation(libs.chucker)
    releaseImplementation(libs.chucker.no.op)
    
    // Testing
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.ui.test.junit4)
    
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
} 