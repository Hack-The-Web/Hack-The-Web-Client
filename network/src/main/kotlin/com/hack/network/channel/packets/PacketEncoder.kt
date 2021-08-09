package com.hack.network.channel.packets

import io.netty.buffer.ByteBuf
import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.MessageToByteEncoder

class PacketEncoder : MessageToByteEncoder<OutgoingPacket>() {
    override fun encode(ctx: ChannelHandlerContext, msg: OutgoingPacket, out: ByteBuf) {
        println("Encoding Packet ${msg.opcode}")
        out.writeByte(msg.opcode)
        out.writeInt(msg.data.size)
        out.writeBytes(msg.data)
    }
}