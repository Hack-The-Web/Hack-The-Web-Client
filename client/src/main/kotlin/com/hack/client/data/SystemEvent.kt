package com.hack.client.data

import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleLongProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.beans.property.SimpleStringProperty
import javafx.scene.Node
import javafx.scene.layout.HBox

class SystemEvent {

    val timeStamp = SimpleLongProperty(this, "time_stamp", 0L)
    val username = SimpleStringProperty(this, "username", "")
    val message = SimpleStringProperty(this, "message", "")
    val slot = SimpleIntegerProperty(this, "slot", 0)

    val actions = SimpleObjectProperty<Node>(this, "actions", HBox())

}