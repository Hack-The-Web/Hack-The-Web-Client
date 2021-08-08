package com.hack.client.api.network.packets

interface OutgoingPacket {

    val opcode: Int
    val data: ByteArray

}