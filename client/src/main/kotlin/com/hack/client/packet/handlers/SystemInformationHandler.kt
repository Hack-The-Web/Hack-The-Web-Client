package com.hack.client.packet.handlers

import com.hack.client.api.network.packets.PacketHandler
import com.hack.client.api.network.packets.incoming.SystemInformationPacket
import com.hack.client.ui.views.gameframe.GameModel
import tornadofx.runLater
import java.time.Instant
import java.time.ZoneId

class SystemInformationHandler(val model: GameModel) : PacketHandler<SystemInformationPacket> {
    override suspend fun handlePacket(value: SystemInformationPacket) {
        runLater {
            model.address.set(value.publicAddress)
            model.totalMoney.set("$${value.totalMoney}")
            model.totalBTC.set("BTC: ${value.totalBTC}")
            val timeStamp = value.serverTime
            val zone = ZoneId.of("UTC")
            val date = Instant.ofEpochSecond(timeStamp).atZone(zone).toLocalDateTime()
            model.serverTime.set("${date.hour}h:${date.minute}m:${date.second}")
            model.onlinePlayers.set("${value.onlinePlayers}")
        }
    }
}