plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.compose.compiler)
    application
}

application {
    mainClass.set("LumoGeneratorKt")
}

dependencies {
    implementation(libs.mosaic)
}