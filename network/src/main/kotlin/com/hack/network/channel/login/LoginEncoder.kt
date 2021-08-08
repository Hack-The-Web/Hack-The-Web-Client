package com.hack.network.channel.login

import com.hack.client.api.network.login.LoginInformation
import io.netty.buffer.ByteBuf
import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.MessageToByteEncoder
import java.nio.charset.Charset

class LoginEncoder : MessageToByteEncoder<LoginInformation>() {
    override fun encode(ctx: ChannelHandlerContext?, msg: LoginInformation, out: ByteBuf) {
        println("Encoding Login!")
        out.writeShort(msg.username.length)
        out.writeShort(msg.password.length)
        out.writeCharSequence(msg.username, Charset.defaultCharset())
        out.writeCharSequence(msg.password, Charset.defaultCharset())
    }
}