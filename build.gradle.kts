val spotlessPluginId = libs.plugins.spotless.get().pluginId

buildscript {
    repositories {
        maven(url = "https://maven.aliyun.com/repository/gradle-plugin")
        maven(url = "https://maven.aliyun.com/repository/google")
        maven(url = "https://maven.aliyun.com/repository/public")
        maven(url = "https://maven.aliyun.com/repository/jcenter")

        // ★ 腾讯云镜像（大厂兜底，速度媲美阿里云）
        maven(url = "https://mirrors.cloud.tencent.com/nexus/repository/maven-public/")

        // ★ 华为云镜像（稳定无劫持，备用首选）
        maven(url = "https://repo.huaweicloud.com/repository/maven/")
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

