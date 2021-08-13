package com.javatar.client.test

import com.hack.client.ui.views.gameframe.GameFrameView
import tornadofx.App
import tornadofx.launch

class TestApp : App(GameFrameView::class)

fun main() {
    launch<TestApp>()
}