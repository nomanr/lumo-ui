val spotlessPluginId = libs.plugins.spotless.get().pluginId

buildscript {
    repositories {
        google()
        mavenCentral()

    }
}
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.compose.compiler) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.kotlin.serialization) apply false
    alias(libs.plugins.org.jetbrains.kotlin.jvm) apply false
    alias(libs.plugins.spotless) apply false
    alias(libs.plugins.vanniktech.maven.publish) apply false
    alias(libs.plugins.kotlin.multiplatform) apply false
    alias(libs.plugins.compose.multiplatform) apply false

}

subprojects {
    apply {
        plugin(spotlessPluginId)
    }

    configure<com.diffplug.gradle.spotless.SpotlessExtension> {
        kotlin {
            target("**/*.kt")
            targetExclude("${layout.buildDirectory}/**/*.kt")

            ktlint()
            suppressLintsFor {
                step = "ktlint"
                shortCode = "standard:function-naming"
            }
            suppressLintsFor {
                step = "ktlint"
                shortCode = "standard:property-naming"
            }
        }

        kotlinGradle {
            target("*.gradle.kts")
            ktlint()
        }
    }

}

