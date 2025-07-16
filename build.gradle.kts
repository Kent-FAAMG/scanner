// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.kapt) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.hilt) apply false
    alias(libs.plugins.spotless) apply false
    alias(libs.plugins.detekt) apply false
}

// Apply spotless to all subprojects
subprojects {
    apply(plugin = "com.diffplug.spotless")
    
    configure<com.diffplug.gradle.spotless.SpotlessExtension> {
        kotlin {
            target("**/*.kt")
            targetExclude("**/build/**/*.kt")
            ktlint(libs.versions.ktlint.get())
            licenseHeaderFile(rootProject.file("spotless/copyright.kt"))
        }
        format("kts") {
            target("**/*.kts")
            targetExclude("**/build/**/*.kts")
        }
        format("xml") {
            target("**/*.xml")
            targetExclude("**/build/**/*.xml")
        }
    }
}

// Apply detekt to all subprojects
subprojects {
    apply(plugin = "io.gitlab.arturbosch.detekt")
    
    configure<io.gitlab.arturbosch.detekt.extensions.DetektExtension> {
        toolVersion = "1.23.6"
        config.setFrom(files("$rootDir/config/detekt/detekt.yml"))
        source.setFrom(files("$rootDir/src", "$rootDir/core", "$rootDir/sdk-qrscanner", "$rootDir/host-app"))
        parallel = true
        reports {
            html {
                required.set(true)
                outputLocation.set(file("$buildDir/reports/detekt/detekt.html"))
            }
            xml {
                required.set(true)
                outputLocation.set(file("$buildDir/reports/detekt/detekt.xml"))
            }
            txt {
                required.set(true)
                outputLocation.set(file("$buildDir/reports/detekt/detekt.txt"))
            }
        }
    }

    tasks.withType<io.gitlab.arturbosch.detekt.Detekt>().configureEach {
        reports {
            html.required.set(true)
            xml.required.set(true)
            txt.required.set(true)
        }
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}