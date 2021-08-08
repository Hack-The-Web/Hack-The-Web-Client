package com.hack.client.api

interface GameClient {
    fun connect(username: String, password: String, onSuccessfulLogin: () -> Unit)
    fun shutdown()
}