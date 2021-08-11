plugins {
    application
    id("org.openjfx.javafxplugin") version "0.0.10"

}

javafx {
    version = "15.0.1"
    modules(
        "javafx.base",
        "javafx.graphics",
        "javafx.controls",
        "javafx.fxml",
        "javafx.web"
    )
}

application {
    mainClass.set("com.hack.launcher.Launcher")
    applicationDefaultJvmArgs = listOf(
        "--add-opens=javafx.graphics/javafx.scene=ALL-UNNAMED",
        "--add-opens=javafx.graphics/com.sun.javafx.scene.traversal=ALL-UNNAMED",
        "--add-opens=javafx.graphics/com.sun.javafx.scene=ALL-UNNAMED",
        "--add-opens=javafx.controls/javafx.scene.control=ALL-UNNAMED"
    )
}

dependencies {
    implementation("no.tornado:tornadofx:2.0.0-SNAPSHOT")
    implementation(project(":network-api"))
    implementation(project(":network"))
    implementation(project(":client"))
}