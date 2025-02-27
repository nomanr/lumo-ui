import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.compose.multiplatform)
    alias(libs.plugins.compose.compiler)
    id("com.android.library")
    kotlin("multiplatform")
}

kotlin {
    androidTarget {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_17)
        }
    }

    iosArm64()
    iosX64()
    iosSimulatorArm64()
    macosX64()
    macosArm64()

//    @OptIn(ExperimentalWasmDsl::class)
//    wasmJs {
//        browser()
//    }

    jvm("desktop") {
        compilations.all {
            compileTaskProvider.configure {
                compilerOptions {
                    jvmTarget.set(JvmTarget.JVM_11)
                }
            }
        }
    }

    sourceSets {
        androidMain.dependencies {
        }
        commonMain.dependencies {
            implementation(compose.components.resources)
            api(compose.runtime)
            api(compose.foundation)
            // Todo - get rid of this at somepoint and use ripple from androidx
            api(compose.material)
            api(compose.ui)
            api(compose.components.resources)
            api(compose.components.uiToolingPreview)
            api(libs.nomanr.composables)
        }
    }
}

android {
    namespace = "com.nomanr.lumo.components"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()
        lint {
            targetSdk = libs.versions.targetSdk.get().toInt()
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

dependencies {
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.tooling)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.foundation)
    implementation(libs.androidx.compose.foundation.layout)

    implementation(libs.nomanr.composables)
    implementation(libs.androidx.compose.ripple)
}
