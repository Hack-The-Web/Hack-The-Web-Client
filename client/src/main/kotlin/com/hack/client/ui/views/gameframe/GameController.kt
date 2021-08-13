package com.hack.client.ui.views.gameframe

import com.hack.client.api.GameClient
import com.hack.client.api.network.packets.incoming.SystemInformationPacket
import com.hack.client.api.network.packets.outgoing.WidgetActionPacket
import com.hack.client.api.session.NetworkSession
import com.hack.client.api.session.packet
import com.hack.client.packet.handlers.SystemInformationHandler
import com.hack.client.packet.sendOpenWidget
import com.hack.client.packet.sendWidgetAction
import com.hack.client.ui.models.nav.ContentTabs
import com.hack.client.ui.scope.GameScope
import com.hack.client.ui.views.LoginView
import com.hack.client.ui.views.content.developer.DeveloperFragment
import com.hack.client.ui.views.content.finances.FinancesFragment
import com.hack.client.ui.views.content.hardware.HardwareFragment
import com.hack.client.ui.views.content.hdb.HackedDatabaseFragment
import com.hack.client.ui.views.content.home.HomeFragment
import com.hack.client.ui.views.content.internet.InternetFragment
import com.hack.client.ui.views.content.logs.LogsFragment
import com.hack.client.ui.views.content.missions.MissionsFragment
import com.hack.client.ui.views.content.software.SoftwareFragment
import com.hack.client.ui.views.content.tasks.TasksFragment
import com.hack.client.ui.views.content.university.UniversityFragment
import javafx.event.ActionEvent
import org.koin.core.context.GlobalContext
import tornadofx.*

class GameController : Controller() {
    override val scope = super.scope as GameScope
    private val session: NetworkSession
        get() = scope.session
    val gameModel: GameModel by inject()


    fun subscribePackets() {
        session.packet(SystemInformationPacket, SystemInformationHandler(gameModel))
    }

    fun watchForWidgetChange() {
        gameModel.activeWidget.addListener { _, old, new ->
            if (old != null && new != null) {
                session.sendOpenWidget(new)
                updateContent(old, new)
            }
        }
    }

    fun updateContentOnLogin() {
        val prevTab = ContentTabs.valueOf(config["prev_tab"] as String? ?: "HOME")
        val currentTab = ContentTabs.valueOf(config["current_tab"] as String? ?: "HOME")
        if(currentTab !== ContentTabs.HOME) {
            gameModel.activeWidget.set(currentTab)
        }
        updateContent(prevTab, currentTab)
    }

    fun updateContent(prevTab: ContentTabs, tab: ContentTabs) {
        when(tab) {
            ContentTabs.HOME -> gameModel.content.set(find<HomeFragment>(scope).root)
            ContentTabs.TASKS -> gameModel.content.set(find<TasksFragment>(scope).root)
            ContentTabs.SOFTWARE -> gameModel.content.set(find<SoftwareFragment>(scope).root)
            ContentTabs.INTERNET -> gameModel.content.set(find<InternetFragment>(scope).root)
            ContentTabs.LOGS -> gameModel.content.set(find<LogsFragment>(scope).root)
            ContentTabs.HARDWARE -> gameModel.content.set(find<HardwareFragment>(scope).root)
            ContentTabs.UNIVERSITY -> gameModel.content.set(find<UniversityFragment>(scope).root)
            ContentTabs.FINANCES -> gameModel.content.set(find<FinancesFragment>(scope).root)
            ContentTabs.HACKED_DATABASE -> gameModel.content.set(find<HackedDatabaseFragment>(scope).root)
            ContentTabs.MISSIONS -> gameModel.content.set(find<MissionsFragment>(scope).root)
            ContentTabs.CLAN -> {}
            ContentTabs.RANKING -> {}
            ContentTabs.FAME -> {}
            ContentTabs.NONE -> gameModel.content.set(null)
            ContentTabs.DEVELOPER -> gameModel.content.set(find<DeveloperFragment>(scope).root)
            ContentTabs.GAMEFRAME -> {}
        }
        with(config) {
            set("prev_tab", prevTab.name)
            set("current_tab", tab.name)
            save()
        }
    }

    fun handleLogoutButtonAction(e: ActionEvent) {
        session.sendWidgetAction(ContentTabs.GAMEFRAME, "logout")
        find<GameFrameView>(scope).replaceWith<LoginView>(sizeToScene = true, centerOnScreen = true)
        scope.client.shutdown()
    }

}