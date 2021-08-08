package com.hack.network.channel.handshake

import com.hack.client.api.network.handshake.HandshakeRequest
import io.netty.buffer.ByteBuf
import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.MessageToByteEncoder

class HandshakeEncoder : MessageToByteEncoder<HandshakeRequest>() {
    override fun encode(ctx: ChannelHandlerContext, msg: HandshakeRequest, out: ByteBuf) {
        println("Encoding ${msg.value} - ${msg.revision}")
        out.writeByte(msg.value)
        if(msg.value == 1) {
            out.writeInt(msg.revision)
        }
    }
}