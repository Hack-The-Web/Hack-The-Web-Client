package com.hack.client.packet.handlers

import com.hack.client.api.network.packets.PacketHandler
import com.hack.client.api.network.packets.incoming.SystemEventPacket
import com.hack.client.data.SystemEvent
import com.hack.client.ui.views.content.events.SystemEventsModel
import tornadofx.runLater

class SystemEventHandler(val model: SystemEventsModel) : PacketHandler<SystemEventPacket> {
    override suspend fun handlePacket(value: SystemEventPacket) {
        runLater {
            if(model.events.containsKey(value.slot)) {
                with(model.events[value.slot]!!) {
                    timeStamp.set(value.date)
                    username.set(value.username)
                    message.set(value.msg)
                }
            } else {
                model.events[value.slot] = SystemEvent().apply {
                    timeStamp.set(value.date)
                    username.set(value.username)
                    message.set(value.msg)
                }
            }
        }
    }
}