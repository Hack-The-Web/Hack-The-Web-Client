package com.hack.network

import com.hack.client.api.session.NetworkSession
import com.hack.network.channel.packets.IncomingPacket
import com.hack.network.channel.packets.OutgoingPacket
import io.netty.channel.ChannelHandlerContext
import io.netty.util.AttributeKey
import kotlinx.coroutines.flow.MutableSharedFlow

class Session(private val channel: ChannelHandlerContext) : NetworkSession<IncomingPacket> {

    val incomingPackets = MutableSharedFlow<IncomingPacket>(extraBufferCapacity = 255)

    override fun sendMessage(message: Any) {
        channel.write(message)
    }

    override fun handleIncomingPacket(packet: IncomingPacket) {
        incomingPackets.tryEmit(packet)
    }

    companion object {
        val SESSION_KEY = AttributeKey.valueOf<NetworkSession<OutgoingPacket>>("session")
    }
}