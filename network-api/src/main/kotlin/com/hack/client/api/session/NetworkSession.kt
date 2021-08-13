package com.hack.client.api.session

import com.hack.client.api.network.packets.GamePacketDecoder
import com.hack.client.api.network.packets.GamePacketEncoder
import com.hack.client.api.network.packets.IncomingPacket
import com.hack.client.api.network.packets.PacketHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*

interface NetworkSession {

    val incomingPackets: MutableSharedFlow<IncomingPacket>

    fun sendOutgoingPacket(transformer: GamePacketEncoder)

    fun handleIncomingPacket(packet: IncomingPacket)

}

inline fun <reified T> NetworkSession.packet(transformer: GamePacketDecoder<T>, handler: PacketHandler<T>) : Job {
    return incomingPackets.filter { it.opcode == transformer.opcode }.transform {
        with(transformer) {
            emit(it.data.decodePacket())
        }
    }.onEach { handler.handlePacket(it) }.launchIn(CoroutineScope(Dispatchers.IO))
}