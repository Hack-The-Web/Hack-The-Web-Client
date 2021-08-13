package com.hack.client

import com.hack.client.ui.views.LoginView
import javafx.application.Platform
import kotlinx.coroutines.asCoroutineDispatcher
import tornadofx.App
import tornadofx.reloadStylesheetsOnFocus
import java.io.File
import java.nio.file.Path
import java.util.concurrent.Executors
import java.util.concurrent.atomic.AtomicBoolean

abstract class Application : App(LoginView::class) {
    override val configBasePath: Path = Path.of("${System.getProperty("user.home")}${File.separator}hack-the-web${File.separator}conf")

    override fun init() {
        super.init()
        reloadStylesheetsOnFocus()
    }

    companion object {
        const val DEV_MODE = true
        const val CSS_LOC = "file:/home/javatar/IdeaProjects/HackTheWebClient/client/src/main/resources/assets"
    }
}