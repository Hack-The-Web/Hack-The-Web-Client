plugins {
    id("org.openjfx.javafxplugin") version "0.0.9"
}

javafx {
    version = "15.0.1"
    modules("javafx.base", "javafx.graphics", "javafx.controls")
}

dependencies {
    implementation("no.tornado:tornadofx:2.0.0-SNAPSHOT")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-javafx:1.5.1")
    implementation(project(":network-api"))
}