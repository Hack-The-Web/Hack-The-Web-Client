package com.hack.client.api

import com.hack.client.api.session.NetworkSession

interface GameClient {

    val session: NetworkSession<*>

    fun connect(username: String, password: String)
}