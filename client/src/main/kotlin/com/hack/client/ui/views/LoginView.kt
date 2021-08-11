package com.hack.client.ui.views

import com.hack.client.api.GameClient
import com.hack.client.ui.models.LoginModel
import com.hack.client.ui.scope.GameScope
import com.hack.client.ui.views.game.GameView
import javafx.beans.binding.Bindings
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.javafx.JavaFx
import tornadofx.*

class LoginView : View("Hack The Web") {

    val loginModel: LoginModel by inject()
    val client: GameClient by di()

    override val root = form {
        fieldset("Login") {
            field("Username") {
                textfield(loginModel.username)
            }
            field("Password") {
                passwordfield(loginModel.password)
            }
            field {
                button {
                    disableWhen(loginModel.username.isEmpty.or(loginModel.password.isEmpty))
                    textProperty().bind(Bindings.createStringBinding({
                        "Login as ${loginModel.username.get()}"
                    }, loginModel.username))
                    action {
                        with(loginModel.config) {
                            set("username", loginModel.username.get())
                            set("password", loginModel.password.get())
                            loginModel.config.save()
                        }
                        flowOf(client)
                            .onEach {
                                client.connect(loginModel.username.get(), loginModel.password.get()) { session ->
                                    flowOf(this@LoginView)
                                        .onEach {
                                            it.replaceWith(find(GameView::class, GameScope(session)), sizeToScene = true, centerOnScreen = true)
                                        }.launchIn(CoroutineScope(Dispatchers.JavaFx))
                                }
                            }.launchIn(CoroutineScope(Dispatchers.IO))
                    }
                }
            }
        }
    }

}