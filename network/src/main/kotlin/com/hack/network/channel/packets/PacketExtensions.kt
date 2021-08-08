package com.hack.network.channel.packets

import com.hack.client.api.network.packets.GamePacketDecoder
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.transform

inline fun <reified T> MutableSharedFlow<IncomingPacket>.packet(transformer: GamePacketDecoder<T>, crossinline action: (T) -> Unit) = transform {
    with(transformer) {
        emit(it.data.decodePacket())
    }
}.onEach { action(it) }