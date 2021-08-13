package com.hack.client.packet.handlers

import com.hack.client.api.network.packets.PacketHandler
import com.hack.client.api.network.packets.incoming.SystemInformationPacket
import com.hack.client.ui.views.gameframe.GameModel
import tornadofx.runLater

class SystemInformationHandler(val model: GameModel) : PacketHandler<SystemInformationPacket> {
    override suspend fun handlePacket(value: SystemInformationPacket) {
        runLater {
            model.address.set(value.publicAddress)
            model.totalMoney.set("$${value.totalMoney}")
            model.totalBTC.set("BTC: ${value.totalBTC}")
        }
    }
}