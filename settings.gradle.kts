pluginManagement {
    includeBuild("lumo-ui/plugin")

    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

rootProject.name = "lumo-ui"
include(":lumo-ui:components-lab:android")
include(":lumo-ui:components-lab:common")
include(":lumo-ui:components-lab:multiplatform")
include(":sample-android:catalogue")
include(":sample-android:ui-components")
include(":sample-multiplatform:catalogue:common")
include(":sample-multiplatform:catalogue:android")

