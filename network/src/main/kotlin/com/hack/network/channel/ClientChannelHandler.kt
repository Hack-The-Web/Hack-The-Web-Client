package com.hack.network.channel

import com.hack.client.api.network.handshake.HandshakeRequest
import com.hack.client.api.network.handshake.HandshakeResponse
import com.hack.client.api.network.login.LoginInformation
import com.hack.network.Session
import com.hack.network.channel.login.LoginDecoder
import com.hack.network.channel.login.LoginEncoder
import com.hack.network.channel.packets.IncomingPacket
import com.hack.network.channel.packets.OutgoingPacket
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.ChannelInboundHandlerAdapter

class ClientChannelHandler(val loginInfo: LoginInformation, val onSuccessfulLogin: (Session) -> Unit) : ChannelInboundHandlerAdapter() {

    override fun channelActive(ctx: ChannelHandlerContext) {
        ctx.writeAndFlush(HandshakeRequest(1, 1))
    }

    override fun channelRead(ctx: ChannelHandlerContext, msg: Any) {
        if(msg is IncomingPacket) {
            val session = ctx.channel().attr(Session.SESSION_KEY).get()
            session.handleIncomingPacket(msg)
        } else if(msg is HandshakeResponse && msg.response == 1) {
            ctx.pipeline().replace("decoder", "decoder", LoginDecoder(onSuccessfulLogin))
            ctx.pipeline().replace("encoder", "encoder", LoginEncoder())
            ctx.writeAndFlush(loginInfo)
        } else {
            ctx.writeAndFlush(msg)
        }
    }

}