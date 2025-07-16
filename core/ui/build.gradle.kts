plugins {
    id("bureau.android.library.compose")
}

android {
    namespace = "com.bureau.qrscanner.core.ui"
}

dependencies {
    api(platform(libs.androidx.compose.bom))
    api(libs.bundles.compose)
    api(libs.androidx.core.ktx)
    api(libs.androidx.lifecycle.runtime.ktx)
    api(libs.androidx.lifecycle.viewmodel.compose)
    api(libs.coil.compose)
    
    debugApi(libs.androidx.ui.tooling)
    debugApi(libs.androidx.ui.test.manifest)
} 