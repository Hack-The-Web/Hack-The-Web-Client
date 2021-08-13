package com.hack.network.channel.login

import com.hack.network.Session
import com.hack.network.channel.packets.PacketDecoder
import com.hack.network.channel.packets.PacketEncoder
import io.netty.buffer.ByteBuf
import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.ByteToMessageDecoder
import io.netty.handler.timeout.IdleStateHandler

class LoginDecoder(val onSuccessfulLogin: (Session) -> Unit) : ByteToMessageDecoder() {
    override fun decode(ctx: ChannelHandlerContext, buf: ByteBuf, out: MutableList<Any>) {
        val successful = buf.readUnsignedByte().toInt() == 1
        if(successful) {
            ctx.pipeline().replace("decoder", "decoder", PacketDecoder())
            ctx.pipeline().replace("encoder", "encoder", PacketEncoder())
            ctx.pipeline().addLast("idle", IdleStateHandler(0, 15, 60))
            val session = Session(ctx)
            ctx.channel().attr(Session.SESSION_KEY).set(session)
            onSuccessfulLogin(session)
        } else {
            ctx.disconnect()
        }
    }
}