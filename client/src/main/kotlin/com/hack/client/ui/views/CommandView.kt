package com.hack.client.ui.views

import com.hack.client.api.GameClient
import com.hack.client.api.network.packets.outgoing.CommandPacket
import com.hack.client.ui.models.CommandModel
import com.hack.client.ui.scope.GameScope
import tornadofx.*

class CommandView : View() {

    override val scope: GameScope = super.scope as GameScope

    val model: CommandModel by inject()
    val client: GameClient by di()

    override val root = form {
        fieldset {
            field("Command") {
                textfield(model.commandString).action(::executeCommand)
                button("Run").action(::executeCommand)
            }
        }
    }

    private fun executeCommand() {
        val command = model.commandString.get()
        val session = scope.session
        println("Sending Command $command")
        session.sendOutgoingPacket(1, CommandPacket(command))
    }

}