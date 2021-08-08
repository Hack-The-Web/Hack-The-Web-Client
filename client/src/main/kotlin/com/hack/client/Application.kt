package com.hack.client

import com.hack.client.api.GameClient
import com.hack.client.ui.views.LoginView
import org.koin.core.context.GlobalContext
import tornadofx.App
import java.io.File
import java.nio.file.Path

abstract class Application : App(LoginView::class) {
    override val configBasePath: Path = Path.of("${System.getProperty("user.home")}${File.separator}hack-the-web${File.separator}conf")

    override fun stop() {
        super.stop()
        val client = GlobalContext.get().get<GameClient>()
        client.shutdown()
        println("Stopping!")
    }
}