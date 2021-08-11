package com.hack.client

import com.hack.client.api.GameClient
import com.hack.client.ui.views.LoginView
import org.koin.core.context.GlobalContext
import tornadofx.App
import tornadofx.reloadStylesheetsOnFocus
import java.io.File
import java.nio.file.Path

abstract class Application : App(LoginView::class) {
    override val configBasePath: Path = Path.of("${System.getProperty("user.home")}${File.separator}hack-the-web${File.separator}conf")

    override fun init() {
        super.init()
        reloadStylesheetsOnFocus()
    }

    override fun stop() {
        super.stop()
        val client = GlobalContext.get().get<GameClient>()
        client.shutdown()
    }

    companion object {
        const val DEV_MODE = true
        const val CSS_LOC = "file:/home/javatar/IdeaProjects/HackTheWebClient/client/src/main/resources/assets"
    }
}