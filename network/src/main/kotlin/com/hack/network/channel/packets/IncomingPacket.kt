package com.hack.network.channel.packets

import com.hack.client.api.network.packets.IncomingPacket
import java.io.DataInputStream

class IncomingPacket(override val opcode: Int, override val data: DataInputStream) : IncomingPacket