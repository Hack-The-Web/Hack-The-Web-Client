plugins {
    kotlin("jvm") version "1.5.10" apply false
}

subprojects {
    group = "com.hack.client"
    version = "1.0-SNAPSHOT"

    apply {
        plugin("kotlin")
    }

    repositories {
        mavenCentral()
    }

    dependencies {
        "implementation"(kotlin("stdlib"))
    }
}
