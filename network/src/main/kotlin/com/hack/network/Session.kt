package com.hack.network

import com.hack.client.api.network.packets.GamePacketEncoder
import com.hack.client.api.network.packets.IncomingPacket
import com.hack.client.api.session.NetworkSession
import com.hack.network.channel.packets.OutgoingPacket
import io.netty.channel.ChannelHandlerContext
import io.netty.util.AttributeKey
import kotlinx.coroutines.flow.MutableSharedFlow
import java.io.ByteArrayOutputStream
import java.io.DataOutputStream

class Session(private val channel: ChannelHandlerContext) : NetworkSession {

    val incomingPackets = MutableSharedFlow<IncomingPacket>(extraBufferCapacity = 255)

    override fun sendOutgoingPacket(opcode: Int, transformer: GamePacketEncoder) {
        val stream = ByteArrayOutputStream()
        val data = DataOutputStream(stream)
        with(transformer) {
            data.encode()
        }
        channel.write(OutgoingPacket(opcode, stream.toByteArray()))
    }

    override fun handleIncomingPacket(packet: IncomingPacket) {
        incomingPackets.tryEmit(packet)
    }

    companion object {
        val SESSION_KEY = AttributeKey.valueOf<NetworkSession>("session")
    }
}