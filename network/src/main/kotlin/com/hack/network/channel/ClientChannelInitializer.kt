package com.hack.network.channel

import com.hack.client.api.network.login.LoginInformation
import com.hack.network.Session
import com.hack.network.channel.handshake.HandshakeDecoder
import com.hack.network.channel.handshake.HandshakeEncoder
import io.netty.channel.ChannelInitializer
import io.netty.channel.socket.nio.NioSocketChannel

class ClientChannelInitializer(val loginInfo: LoginInformation, val onSuccessfulLogin: (Session) -> Unit) : ChannelInitializer<NioSocketChannel>() {
    override fun initChannel(ch: NioSocketChannel) {
        with(ch.pipeline()) {
            addLast("decoder", HandshakeDecoder())
            addLast("encoder", HandshakeEncoder())
            addLast("handler", ClientChannelHandler(loginInfo, onSuccessfulLogin))
        }
    }
}