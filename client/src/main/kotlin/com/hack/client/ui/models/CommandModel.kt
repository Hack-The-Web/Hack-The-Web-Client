package com.hack.client.ui.models

import com.hack.client.api.session.NetworkSession
import javafx.beans.property.SimpleObjectProperty
import javafx.beans.property.SimpleStringProperty
import tornadofx.ViewModel

class CommandModel : ViewModel() {

    val session = bind { SimpleObjectProperty<NetworkSession>(this, "session") }
    val commandString = bind { SimpleStringProperty(this, "command_string") }

}