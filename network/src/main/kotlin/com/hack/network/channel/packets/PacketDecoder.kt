package com.hack.network.channel.packets

import io.netty.buffer.ByteBuf
import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.ByteToMessageDecoder
import java.io.ByteArrayInputStream
import java.io.DataInputStream

class PacketDecoder : ByteToMessageDecoder() {
    override fun decode(ctx: ChannelHandlerContext, buffer: ByteBuf, out: MutableList<Any>) {
        if(buffer.readableBytes() >= 5) {
            val opcode = buffer.readUnsignedByte().toInt()
            val size = buffer.readInt()
            if (size > 0 && size <= buffer.readableBytes()) {
                val bytes = ByteArray(size)
                buffer.readBytes(bytes)
                val data = DataInputStream(ByteArrayInputStream(bytes))
                out.add(IncomingPacket(opcode, data))
            } else {
                buffer.readerIndex(buffer.writerIndex())
            }
        } else {
            buffer.readerIndex(buffer.writerIndex())
        }
    }
}