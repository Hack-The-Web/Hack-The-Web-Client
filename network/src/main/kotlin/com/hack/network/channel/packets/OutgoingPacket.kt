package com.hack.network.channel.packets

import com.hack.client.api.network.packets.OutgoingPacket
import java.io.DataInputStream
import java.io.DataOutputStream

class OutgoingPacket(override val opcode: Int, override val data: DataOutputStream) : OutgoingPacket