package com.hack.network

import com.hack.client.api.GameClient
import com.hack.client.api.network.login.LoginInformation
import com.hack.client.api.session.NetworkSession
import com.hack.network.channel.ClientChannelInitializer
import com.hack.network.channel.packets.IncomingPacket
import com.hack.network.channel.packets.OutgoingPacket
import io.netty.bootstrap.Bootstrap
import io.netty.channel.Channel
import io.netty.channel.ChannelOption
import io.netty.channel.EventLoopGroup
import io.netty.channel.nio.NioEventLoopGroup
import io.netty.channel.socket.nio.NioSocketChannel
import kotlinx.coroutines.asCoroutineDispatcher
import org.koin.dsl.module
import java.util.concurrent.Executors
import java.util.concurrent.ThreadFactory

class NetworkClient(private val host: String, private val port: Int) : GameClient {

    private val bootstrap = Bootstrap()
    private val eventGroup: EventLoopGroup = NioEventLoopGroup(1, ThreadFactory { Thread(it).also { th -> th.isDaemon = true } })
    private lateinit var channel: Channel

    override fun connect(username: String, password: String, onSuccessfulLogin: (NetworkSession) -> Unit) {
        try {
            bootstrap
                .group(eventGroup)
                .channel(NioSocketChannel::class.java)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .handler(ClientChannelInitializer(LoginInformation(username, password), onSuccessfulLogin))
            val f = bootstrap.connect(host, port).sync()
            val channel = f.channel()
            this.channel = channel
            channel.closeFuture().sync()
        } finally {
            eventGroup.shutdownGracefully()
        }
    }

    override fun shutdown() {
        eventGroup.shutdownGracefully()
    }

    companion object {
        val module = module {
            factory<GameClient> { NetworkClient("127.0.0.1", 50000) }
        }
    }
}