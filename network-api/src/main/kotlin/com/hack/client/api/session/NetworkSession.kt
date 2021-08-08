package com.hack.client.api.session

interface NetworkSession<P> {

    fun sendMessage(message: Any)

    fun handleIncomingPacket(packet: P)

}