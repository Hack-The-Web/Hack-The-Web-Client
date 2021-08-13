package com.hack.client.ui.views.gameframe

import com.hack.client.ui.models.nav.ContentTabs
import javafx.beans.property.SimpleObjectProperty
import javafx.beans.property.SimpleStringProperty
import javafx.scene.Node
import tornadofx.ViewModel

class GameModel : ViewModel() {

    val address = bind { SimpleStringProperty(this, "address") }
    val serverTime = bind { SimpleStringProperty(this, "server_time") }
    val onlinePlayers = bind { SimpleStringProperty(this, "online_players") }
    val totalMoney = bind { SimpleStringProperty(this, "total_money") }
    val totalBTC = bind { SimpleStringProperty(this, "total_btc") }
    val location = bind { SimpleStringProperty(this, "location") }

    val content = bind { SimpleObjectProperty<Node>(this, "content") }

    val activeWidget = bind { SimpleObjectProperty(this, "active_widget", ContentTabs.HOME) }
}