package com.hack.launcher

import com.hack.client.Application
import com.hack.network.NetworkClient
import org.koin.core.context.GlobalContext
import org.koin.core.context.GlobalContext.startKoin
import tornadofx.DIContainer
import tornadofx.FX
import kotlin.reflect.KClass

class Launcher : Application() {
    override fun init() {
        startKoin {
            modules(NetworkClient.module)
        }
        FX.dicontainer = object : DIContainer {
            override fun <T : Any> getInstance(type: KClass<T>): T {
                return GlobalContext.get().get(type)
            }
        }
        super.init()
    }
}