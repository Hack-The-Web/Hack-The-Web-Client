package com.hack.network.channel.packets

import io.netty.buffer.ByteBuf
import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.ByteToMessageDecoder
import java.io.ByteArrayInputStream
import java.io.DataInputStream

class PacketDecoder : ByteToMessageDecoder() {
    override fun decode(ctx: ChannelHandlerContext, buf: ByteBuf, out: MutableList<Any>) {
        val opcode = buf.readUnsignedByte().toInt()
        println("Decoding Packet $opcode")
        val size = buf.readInt()
        val bytes = ByteArray(size)
        buf.readBytes(bytes)
        val data = DataInputStream(ByteArrayInputStream(bytes))
        out.add(IncomingPacket(opcode, data))
    }
}