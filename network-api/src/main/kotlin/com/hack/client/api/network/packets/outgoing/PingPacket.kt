package com.hack.client.api.network.packets.outgoing

import com.hack.client.api.network.packets.GamePacketEncoder
import java.io.DataOutputStream

class PingPacket(val isFXActive: Boolean) : GamePacketEncoder {

    override val opcode: Int = 222

    override fun DataOutputStream.encode() {
        writeBoolean(isFXActive)
    }

}