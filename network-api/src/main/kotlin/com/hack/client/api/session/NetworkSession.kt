package com.hack.client.api.session

import com.hack.client.api.network.packets.GamePacketEncoder
import com.hack.client.api.network.packets.IncomingPacket

interface NetworkSession {

    fun sendOutgoingPacket(opcode: Int, transformer: GamePacketEncoder)

    fun handleIncomingPacket(packet: IncomingPacket)

}