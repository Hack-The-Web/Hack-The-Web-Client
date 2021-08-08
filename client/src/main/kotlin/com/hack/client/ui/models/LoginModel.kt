package com.hack.client.ui.models

import javafx.beans.property.SimpleStringProperty
import tornadofx.ViewModel

class LoginModel : ViewModel() {
    val username = bind { SimpleStringProperty(this, "username", config.string("username") ?: "") }
    val password = bind { SimpleStringProperty(this, "password", config.string("password") ?: "") }
}