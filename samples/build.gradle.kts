plugins {
    kotlin("jvm")
}

group = "com.github.RowDaBoat"
version = "1.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":"))
}
