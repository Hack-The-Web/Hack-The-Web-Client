package com.hack.network.channel.packets

import com.hack.client.api.network.packets.GamePacketEncoder
import io.netty.buffer.ByteBuf
import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.MessageToByteEncoder
import java.io.ByteArrayOutputStream
import java.io.DataOutputStream

class PacketEncoder : MessageToByteEncoder<GamePacketEncoder>() {
    override fun encode(ctx: ChannelHandlerContext, msg: GamePacketEncoder, out: ByteBuf) {
        val output = ByteArrayOutputStream()
        val data = DataOutputStream(output)
        with(msg) {
            data.encode()
        }
        out.writeBytes(output.toByteArray())
    }
}