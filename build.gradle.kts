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

}

subprojects {
    apply {
        plugin(spotlessPluginId)
    }

    configure<com.diffplug.gradle.spotless.SpotlessExtension> {
        kotlin {
            target("**/*.kt")
            targetExclude("${layout.buildDirectory}/**/*.kt")
            targetExclude("bin/**/*.kt")

//            ktlint("0.41.0").userData(
//                mapOf(
//                    "android" to "true",
//                    "disabled_rules" to "no-wildcard-imports",
//                    "max_line_length" to "off"
//                )
//            )
        }
    }

}

