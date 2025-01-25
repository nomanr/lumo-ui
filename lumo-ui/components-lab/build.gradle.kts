import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.compose.multiplatform)
    alias(libs.plugins.compose.compiler)
    id("com.android.library")
    kotlin("multiplatform")
}

kotlin {
// TODO: Uncomment this block to enable all the targets
//    applyDefaultHierarchyTemplate()
//
//    targets.all {
//        compilations.all {
//            compileTaskProvider.configure {
//                compilerOptions {
//                    progressiveMode.set(true)
//                    // Disable warnings about expect/actual classes
//                    freeCompilerArgs.addAll("-Xexpect-actual-classes")
//                }
//            }
//        }
//    }
//
//    iosArm64()
//    iosX64()
//    iosSimulatorArm64()
//    macosX64()
//    macosArm64()

    androidTarget {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_17)
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64(),
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "lumo-ui"
            isStatic = true
        }
    }
    sourceSets {
        commonMain.dependencies {
            implementation(compose.components.resources)
            api(compose.runtime)
            api(compose.foundation)
            // Todo - get rid of this at somepoint and use ripple from androidx
            api(compose.material)
//            api(libs.androidx.compose.ripple)
            api(compose.materialIconsExtended)
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
    implementation(libs.androidx.compose.material.iconsExtended)

    implementation(libs.nomanr.composables)
    implementation(libs.androidx.compose.ripple)
}
