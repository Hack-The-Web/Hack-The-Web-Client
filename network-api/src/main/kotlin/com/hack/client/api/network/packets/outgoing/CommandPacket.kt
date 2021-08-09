package com.hack.client.api.network.packets.outgoing

import com.hack.client.api.network.packets.GamePacketEncoder
import java.io.DataOutputStream

class CommandPacket(val command: String) : GamePacketEncoder {
    override fun DataOutputStream.encode() {
        writeUTF(command)
    }
}