package com.hack.client.api.network.packets

import java.io.DataInputStream

interface IncomingPacket {

    val opcode: Int
    val data: DataInputStream

}