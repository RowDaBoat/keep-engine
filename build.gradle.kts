plugins {
    kotlin("jvm")
    `maven-publish`
}

repositories {
    mavenLocal()
    mavenCentral()
}

group = "com.github.RowDaBoat"
version = "1.0-SNAPSHOT"

dependencies {
}

publishing {
    publications {
        create<MavenPublication>("keep-engine") {
            from(components["kotlin"])
        }
    }

    repositories {
        mavenLocal()

        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/RowDaBoat/keep-engine")

            credentials {
                username = System.getenv("GITHUB_ACTOR")
                password = System.getenv("GITHUB_TOKEN")
            }
        }
    }
}
