package com.hack.network.channel.login

import io.netty.buffer.ByteBuf
import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.ByteToMessageDecoder

class LoginDecoder : ByteToMessageDecoder() {
    override fun decode(ctx: ChannelHandlerContext, buf: ByteBuf, out: MutableList<Any>) {
        val successful = buf.readUnsignedByte().toInt() == 1
        if(successful) {
            ctx.pipeline().replace("decoder", "decoder", LoginDecoder())
        } else {
            buf.release()
            ctx.disconnect()
        }
    }
}