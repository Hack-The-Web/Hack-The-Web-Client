package com.hack.client.api.network.packets

import java.io.DataInputStream

interface GamePacketDecoder<T> {

    val opcode: Int

    fun DataInputStream.decodePacket() : T

}