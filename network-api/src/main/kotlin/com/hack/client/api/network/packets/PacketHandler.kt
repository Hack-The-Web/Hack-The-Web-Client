package com.hack.client.api.network.packets

import org.koin.core.component.KoinComponent

fun interface PacketHandler<T> : KoinComponent {

    suspend fun handlePacket(value: T)

}