package com.hack.client

import com.hack.client.ui.views.LoginView
import tornadofx.App

abstract class Application : App(LoginView::class)