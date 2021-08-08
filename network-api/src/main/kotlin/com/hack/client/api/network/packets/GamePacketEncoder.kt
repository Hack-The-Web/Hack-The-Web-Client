package com.hack.client.api.network.packets

import java.io.DataOutputStream

fun interface GamePacketEncoder {

    fun DataOutputStream.encode()

}