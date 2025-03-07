pluginManagement {
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
    }
}

rootProject.name = "EMTest"
include(":app")
include(":core")
include(":core-ui")
include(":data")
include(":features")
include(":features:favorites")
include(":features:favorites:presentation")
include(":features:main")
include(":features:account")
include(":features:account:presentation")
include(":features:main:presentation")
include(":features:main:domain")
include(":features:favorites:domain")
