package com.hack.client.api

import com.hack.client.api.session.NetworkSession

interface GameClient {
    fun connect(username: String, password: String, onSuccessfulLogin: (NetworkSession) -> Unit)
    fun shutdown()
}
