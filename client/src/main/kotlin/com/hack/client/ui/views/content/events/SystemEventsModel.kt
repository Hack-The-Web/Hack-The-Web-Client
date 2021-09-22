package com.hack.client.ui.views.content.events

import com.hack.client.data.SystemEvent
import javafx.beans.property.SimpleMapProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.collections.FXCollections
import tornadofx.ViewModel

class SystemEventsModel : ViewModel() {

    val events = bind { SimpleMapProperty<Int, SystemEvent>(FXCollections.observableHashMap()) }

    val selected = bind { SimpleObjectProperty<SystemEvent>(this, "selected") }

}