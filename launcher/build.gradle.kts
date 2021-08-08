plugins {
    application
    id("org.openjfx.javafxplugin") version "0.0.9"

}

javafx {
    version = "15.0.1"
    modules("javafx.base", "javafx.graphics", "javafx.controls")
}

application {
    mainClass.set("com.hack.launcher.Launcher")
}

dependencies {
    implementation("no.tornado:tornadofx:2.0.0-SNAPSHOT")
    implementation(project(":network-api"))
    implementation(project(":network"))
    implementation(project(":client"))
}