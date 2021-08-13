package com.hack.client.ui.scope

import com.hack.client.api.GameClient
import com.hack.client.api.session.NetworkSession
import tornadofx.Scope

class GameScope(val client: GameClient, val session: NetworkSession) : Scope()