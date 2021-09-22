package com.hack.client.api.network.packets.incoming

import com.hack.client.api.network.packets.GamePacketDecoder
import com.hack.client.api.network.packets.buffer.BufferExtensions.readString
import java.io.DataInputStream

class SystemInformationPacket(val displayName: String,
                              val diskUsage: Int,
                              val ramUsage: Int,
                              val cpuUsage: Int,
                              val networkCardUsage: Int,
                              val totalMoney: Int,
                              val totalBTC: Double,
                              val serverTime: Long,
                              val onlinePlayers: Int,
                              val publicAddress: String) {
    companion object : GamePacketDecoder<SystemInformationPacket> {
        override val opcode: Int = 1
        override fun DataInputStream.decodePacket(): SystemInformationPacket {
            return SystemInformationPacket(
                readString(),
                readInt(),
                readInt(),
                readInt(),
                readInt(),
                readInt(),
                readDouble(),
                readLong(),
                readInt(),
                readString()
            )
        }
    }
}