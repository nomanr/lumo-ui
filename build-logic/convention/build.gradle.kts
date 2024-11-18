plugins {
    `kotlin-dsl`
}


java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    compileOnly(libs.android.gradle.plugin)
    compileOnly(libs.vanniktech.gradle.plugin)

}

tasks {
    validatePlugins {
        enableStricterValidation = true
        failOnWarning = true
    }
}

gradlePlugin {
    plugins{
        register("mavenPublishingPlugin") {
            id = "com.nomanr.composeui.publishing-plugin"
            implementationClass = "com.nomanr.composeui.plugins.PublishingPlugin"
        }
    }
}