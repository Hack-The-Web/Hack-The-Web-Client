package com.hack.client.ui.views

import com.hack.client.ui.models.CommandModel
import tornadofx.*

class CommandView : View() {

    val model: CommandModel by inject()

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

    }

}