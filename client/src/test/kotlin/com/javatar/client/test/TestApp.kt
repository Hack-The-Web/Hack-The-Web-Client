package com.javatar.client.test

import com.hack.client.ui.views.game.GameView
import tornadofx.App
import tornadofx.launch

class TestApp : App(GameView::class)

fun main() {
    launch<TestApp>()
}