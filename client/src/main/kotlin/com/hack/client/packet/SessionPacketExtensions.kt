package com.hack.client.packet

import com.hack.client.api.network.packets.outgoing.WidgetActionPacket
import com.hack.client.api.network.packets.outgoing.WidgetOpenPacket
import com.hack.client.api.session.NetworkSession
import com.hack.client.ui.models.nav.ContentTabs

fun NetworkSession.sendWidgetAction(tab: ContentTabs, actionId: String) {
    sendOutgoingPacket(WidgetActionPacket(tab.name, actionId))
}

fun NetworkSession.sendOpenWidget(tab: ContentTabs) {
    sendOutgoingPacket(WidgetOpenPacket(tab.name))
}