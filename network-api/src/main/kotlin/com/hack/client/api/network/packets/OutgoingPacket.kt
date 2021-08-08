package com.hack.client.api.network.packets

import java.io.DataOutputStream

interface OutgoingPacket {

    val opcode: Int
    val data: DataOutputStream

}