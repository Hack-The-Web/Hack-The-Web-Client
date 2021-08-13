package com.hack.network.channel.handshake

import com.hack.client.api.network.handshake.HandshakeResponse
import com.hack.network.channel.login.LoginDecoder
import com.hack.network.channel.login.LoginEncoder
import io.netty.buffer.ByteBuf
import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.ByteToMessageDecoder

class HandshakeDecoder : ByteToMessageDecoder() {
    override fun decode(ctx: ChannelHandlerContext, buf: ByteBuf, out: MutableList<Any>) {
        val successful = buf.readBoolean()
        println("Finishing Handshake! $successful")
        if(successful) {
            out.add(HandshakeResponse(1))
        } else {
            ctx.disconnect()
        }
    }
}