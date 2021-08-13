plugins {
    id("org.openjfx.javafxplugin") version "0.0.10"
}

javafx {
    version = "15.0.1"
    modules("javafx.graphics")
}

dependencies {
    implementation("io.netty:netty-all:4.1.65.Final")
    implementation(project(":network-api"))
}