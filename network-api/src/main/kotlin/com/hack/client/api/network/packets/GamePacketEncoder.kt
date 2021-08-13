package com.hack.client.api.network.packets

import java.io.DataOutputStream

interface GamePacketEncoder {
    val opcode: Int
    fun DataOutputStream.encode()
}