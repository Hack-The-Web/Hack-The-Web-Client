package com.hack.client.ui.views.content.events

import com.hack.client.data.SystemEvent
import javafx.beans.binding.Bindings
import javafx.scene.control.TableView
import javafx.scene.layout.AnchorPane
import tornadofx.*
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset

class SystemEventsFragment : Fragment() {

    override val root: AnchorPane by fxml("/assets/content/logs.fxml")

    val model: SystemEventsModel by inject()
    val ctrl: LogsController by inject()

    val eventTable: TableView<SystemEvent> by fxid()

    init {

        text("") {
            style = "-fx-padding: 0 0 0 20;"
            translateY = -5.0
        }

        with(eventTable) {
            itemsProperty().bind(Bindings.createObjectBinding({
                model.events.values.sortedBy { it.slot.get() }.asObservable()
            }, model.events))
            column("Time Stamp", SystemEvent::timeStamp) {
                this.minWidth = 150.0
                cellFormat {
                    val timeStamp = this.item.toLong()
                    val zone = ZoneId.of("UTC")
                    val date = Instant.ofEpochSecond(timeStamp).atZone(zone).toLocalDateTime()
                    text = "${date.hour}h ${date.minute}m ${date.second}s"
                }
            }
            column("Account", SystemEvent::username) {
                this.minWidth = 200.0
                cellFormat {
                    text = this.item
                }
            }
            column("Information", SystemEvent::message) {
                this.minWidth = 600.0
                cellFormat {
                    text = this.item
                }
            }
            column("Actions", SystemEvent::actions) {
                this.maxWidth = 120.0
                cellFormat {
                    graphic = this.item
                }
            }
            smartResize()
        }
    }

}