package com.hack.client.api.network.packets.incoming

import com.hack.client.api.network.packets.GamePacketDecoder
import com.hack.client.api.network.packets.buffer.BufferExtensions.readString
import java.io.DataInputStream

class SystemEventPacket(val date: Long, val slot: Int, val username: String, val msg: String) {
    companion object : GamePacketDecoder<SystemEventPacket> {
        override val opcode: Int = 2
        override fun DataInputStream.decodePacket(): SystemEventPacket {
            return SystemEventPacket(readLong(), readInt(), readString(), readString())
        }
    }
}