package com.hack.network

import com.hack.client.api.GameClient
import com.hack.client.api.network.login.LoginInformation
import com.hack.client.api.session.NetworkSession
import com.hack.network.channel.ClientChannelInitializer
import com.hack.network.channel.packets.OutgoingPacket
import io.netty.bootstrap.Bootstrap
import io.netty.channel.Channel
import io.netty.channel.ChannelOption
import io.netty.channel.nio.NioEventLoopGroup
import io.netty.channel.socket.nio.NioSocketChannel
import kotlinx.coroutines.asCoroutineDispatcher
import org.koin.dsl.module

class NetworkClient(private val host: String, private val port: Int) : GameClient {

    private val bootstrap = Bootstrap()
    private val eventGroup = NioEventLoopGroup()
    private val dispatcher = eventGroup.asCoroutineDispatcher()
    private lateinit var channel: Channel

    override val session: NetworkSession<OutgoingPacket> by lazy {
        channel.attr(Session.SESSION_KEY).get()
    }

    override fun connect(username: String, password: String) {
        try {
            bootstrap
                .group(eventGroup)
                .channel(NioSocketChannel::class.java)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .handler(ClientChannelInitializer(LoginInformation(username, password)))

            val f = bootstrap.connect(host, port).sync()
            val channel = f.channel()
            this.channel = channel
            channel.closeFuture().sync()
        } finally {
            eventGroup.shutdownGracefully()
        }
    }

    companion object {
        val module = module {
            single<GameClient> { NetworkClient("127.0.0.1", 50000) }
        }
    }
}