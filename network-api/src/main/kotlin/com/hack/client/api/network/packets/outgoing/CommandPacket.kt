package com.hack.client.api.network.packets.outgoing

import com.hack.client.api.network.packets.GamePacketEncoder
import java.io.DataOutputStream

class CommandPacket(val command: String) : GamePacketEncoder {
    override val opcode: Int = 1
    override fun DataOutputStream.encode() {
        writeUTF(command)
    }
}