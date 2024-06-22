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

rootProject.name = "kobold-keep"

include("keep-engine")
include("kobold-keep")
include("kobold-keep-game")
