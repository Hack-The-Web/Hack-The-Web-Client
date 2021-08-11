package com.hack.client.ui.views.game

import com.hack.client.ui.models.nav.ContentTabs
import com.hack.client.ui.scope.GameScope
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
import tornadofx.*

class GameController : Controller() {
    override val scope: Scope = super.scope as GameScope
    val gameModel: GameModel by inject()

    fun watchForWidgetChange() {
        gameModel.activeWidget.addListener { _, old, new ->
            if (old != null && new != null) {
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
            ContentTabs.CLAN -> TODO()
            ContentTabs.RANKING -> TODO()
            ContentTabs.FAME -> TODO()
            ContentTabs.NONE -> { gameModel.content.set(null) }
            ContentTabs.DEVELOPER -> gameModel.content.set(find<DeveloperFragment>(scope).root)
        }
        with(config) {
            set("prev_tab", prevTab.name)
            set("current_tab", tab.name)
            save()
        }
    }

}