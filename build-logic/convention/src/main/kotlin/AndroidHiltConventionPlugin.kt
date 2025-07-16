import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidHiltConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("com.google.dagger.hilt.android")
            pluginManager.apply("kotlin-kapt")
            
            dependencies {
                add("implementation", "com.google.dagger:hilt-android:2.48")
                add("kapt", "com.google.dagger:hilt-compiler:2.48")
            }
        }
    }
} 