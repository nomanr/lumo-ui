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
include(":lumo-ui:components-lab")
include(":sample-android:catalogue")
include(":sample-multiplatform:ui-components")
include(":sample-multiplatform:catalogue:composeApp")

