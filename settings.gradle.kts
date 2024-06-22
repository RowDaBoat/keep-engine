pluginManagement {
    plugins {
        val kotlinVersion = "2.0.0"

         kotlin("jvm") version kotlinVersion apply false
    }

    repositories {
        mavenCentral()
        gradlePluginPortal()
    }
}

rootProject.name = "keep-engine"

include("samples")
