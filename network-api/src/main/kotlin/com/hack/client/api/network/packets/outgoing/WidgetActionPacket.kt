package com.hack.client.api.network.packets.outgoing

import com.hack.client.api.network.packets.GamePacketEncoder
import java.io.DataOutputStream

class WidgetActionPacket(val tab: String, private val actionId: String) : GamePacketEncoder {
    override val opcode: Int = 2
    override fun DataOutputStream.encode() {
        writeUTF(tab)
        writeUTF(actionId)
    }
}