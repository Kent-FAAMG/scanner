plugins {
    id("bureau.android.library.compose")
    id("bureau.android.hilt")
    id("kotlin-parcelize")
    id("maven-publish")
}

android {
    namespace = "com.bureau.qrscanner.sdk"
    
    publishing {
        singleVariant("release") {
            withSourcesJar()
            withJavadocJar()
        }
    }
}

dependencies {
    api(project(":core:ui"))
    api(project(":core:network"))
    
    // Compose & UI
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.bundles.compose)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.activity.compose)
    implementation(libs.hilt.navigation.compose)
    
    // Camera & QR
    implementation(libs.bundles.camerax)
    implementation(libs.google.mlkit.barcode.scanning)
    
    // Permissions
    implementation(libs.accompanist.permissions)
    
    // Testing
    testImplementation(libs.bundles.testing)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.ui.test.junit4)
    
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}

publishing {
    publications {
        register<MavenPublication>("release") {
            groupId = "com.bureau.sdk"
            artifactId = "qrscanner"
            version = "1.0.0"
            
            afterEvaluate {
                from(components["release"])
            }
            
            pom {
                name.set("Bureau QR Scanner SDK")
                description.set("White-label QR scanning SDK for government ID documents")
                url.set("https://github.com/bureau-id/qrscanner-sdk")
                
                licenses {
                    license {
                        name.set("MIT License")
                        url.set("https://opensource.org/licenses/MIT")
                    }
                }
                
                developers {
                    developer {
                        id.set("bureau")
                        name.set("Bureau ID")
                        email.set("dev@bureau.id")
                    }
                }
            }
        }
    }
} 