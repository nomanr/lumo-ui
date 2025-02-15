pluginManagement {
    includeBuild("lumo-ui/plugin")

    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}

rootProject.name = "lumo-ui"
include(":lumo-ui:components-lab")

include(":sample-android:catalogue")
include(":sample-android:ui-components")

include(":sample-multiplatform:catalogue:common")
include(":sample-multiplatform:catalogue:android")
include(":sample-multiplatform:catalogue:desktop")
include(":sample-multiplatform:catalogue:web")
include(":sample-multiplatform:ui-components")

