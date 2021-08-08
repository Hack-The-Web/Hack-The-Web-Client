package com.hack.network.channel.login

import com.hack.network.channel.packets.PacketDecoder
import com.hack.network.channel.packets.PacketEncoder
import io.netty.buffer.ByteBuf
import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.ByteToMessageDecoder

class LoginDecoder(val onSuccessfulLogin: () -> Unit) : ByteToMessageDecoder() {
    override fun decode(ctx: ChannelHandlerContext, buf: ByteBuf, out: MutableList<Any>) {
        val successful = buf.readUnsignedByte().toInt() == 1
        if(successful) {
            ctx.pipeline().replace("decoder", "decoder", PacketDecoder())
            ctx.pipeline().replace("encoder", "encoder", PacketEncoder())
            onSuccessfulLogin()
        } else {
            buf.release()
            ctx.disconnect()
        }
    }
}