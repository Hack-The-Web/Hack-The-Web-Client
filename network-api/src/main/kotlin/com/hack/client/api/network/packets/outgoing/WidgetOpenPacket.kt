package com.hack.client.api.network.packets.outgoing

import com.hack.client.api.network.packets.GamePacketEncoder
import java.io.DataOutputStream

class WidgetOpenPacket(val tab: String) : GamePacketEncoder {
    override val opcode: Int = 3
    override fun DataOutputStream.encode() {
        writeUTF(tab)
    }
}