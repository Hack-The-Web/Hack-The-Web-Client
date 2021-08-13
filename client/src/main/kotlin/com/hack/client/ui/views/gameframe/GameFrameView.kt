package com.hack.client.ui.views.gameframe

import com.hack.client.ui.models.nav.ContentTabs
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.layout.AnchorPane
import javafx.scene.layout.HBox
import javafx.scene.layout.Pane
import tornadofx.View
import tornadofx.replaceChildren

class GameFrameView : View("Hack The Web") {
    override val root: Pane by fxml("/assets/game.fxml")

    val gameModel: GameModel by inject()
    val controller: GameController by inject()

    val content: AnchorPane by fxid()
    val homeBtn: HBox by fxid()
    val tasksBtn: HBox by fxid()
    val softwareBtn: HBox by fxid()
    val internetBtn: HBox by fxid()
    val logsBtn: HBox by fxid()
    val hardwareBtn: HBox by fxid()
    val universityBtn: HBox by fxid()
    val financesBtn: HBox by fxid()
    val hackedBtn: HBox by fxid()
    val missionsBtn: HBox by fxid()
    val clanBtn: HBox by fxid()
    val rankingBtn: HBox by fxid()
    val fameBtn: HBox by fxid()
    val devBtn: HBox by fxid()
    val breadcrumb: HBox by fxid()

    val userBtn: Button by fxid()
    val emailBtn: Button by fxid()
    val settingBtn: Button by fxid()
    val logoutBtn: Button by fxid()

    val addressLabel: Label by fxid()
    val serverTime: Label by fxid()
    val onlinePlayers: Label by fxid()
    val totalMoney: Label by fxid()
    val totalBTC: Label by fxid()
    val locationLabel: Label by fxid()

    init {
        controller.watchForWidgetChange()
        controller.subscribePackets()
        addressLabel.textProperty().bind(gameModel.address)
        serverTime.textProperty().bind(gameModel.serverTime)
        onlinePlayers.textProperty().bind(gameModel.onlinePlayers)
        totalMoney.textProperty().bind(gameModel.totalMoney)
        totalBTC.textProperty().bind(gameModel.totalBTC)
        locationLabel.textProperty().bind(gameModel.location)

        homeBtn.setOnMouseClicked { gameModel.activeWidget.set(ContentTabs.HOME) }
        tasksBtn.setOnMouseClicked { gameModel.activeWidget.set(ContentTabs.TASKS) }
        softwareBtn.setOnMouseClicked { gameModel.activeWidget.set(ContentTabs.SOFTWARE) }
        internetBtn.setOnMouseClicked { gameModel.activeWidget.set(ContentTabs.INTERNET) }
        logsBtn.setOnMouseClicked { gameModel.activeWidget.set(ContentTabs.LOGS) }
        hardwareBtn.setOnMouseClicked { gameModel.activeWidget.set(ContentTabs.HARDWARE) }
        universityBtn.setOnMouseClicked { gameModel.activeWidget.set(ContentTabs.HARDWARE) }
        financesBtn.setOnMouseClicked { gameModel.activeWidget.set(ContentTabs.FINANCES) }
        hackedBtn.setOnMouseClicked { gameModel.activeWidget.set(ContentTabs.HACKED_DATABASE) }
        missionsBtn.setOnMouseClicked { gameModel.activeWidget.set(ContentTabs.MISSIONS) }
        clanBtn.setOnMouseClicked { gameModel.activeWidget.set(ContentTabs.CLAN) }
        rankingBtn.setOnMouseClicked { gameModel.activeWidget.set(ContentTabs.RANKING) }
        fameBtn.setOnMouseClicked { gameModel.activeWidget.set(ContentTabs.FAME) }
        devBtn.setOnMouseClicked { gameModel.activeWidget.set(ContentTabs.DEVELOPER) }

        logoutBtn.setOnAction { controller.handleLogoutButtonAction(it) }

        gameModel.content.addListener { _, old, new ->
            if(old != new && new != null) {
                content.replaceChildren(new)
            }
        }

        controller.updateContentOnLogin()
    }

}