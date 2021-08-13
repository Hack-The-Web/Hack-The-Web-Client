package com.hack.client.ui.models.nav

enum class ContentTabs(val displayName: String) {
    HOME("Home"),
    TASKS("Task Manager"),
    SOFTWARE("Software"),
    INTERNET("Internet"),
    LOGS("Logs"),
    HARDWARE("Hardware"),
    UNIVERSITY("University"),
    FINANCES("Finances"),
    HACKED_DATABASE("Hacked Database"),
    MISSIONS("Missions"),
    CLAN("Clan"),
    RANKING("Ranking"),
    FAME("Hall of Fame"),
    NONE(""),
    DEVELOPER("Developer Tools"),
    GAMEFRAME("game_frame");

    override fun toString(): String {
        return this.displayName
    }

    companion object {

        private val values = values()

        fun findByCrumbName(name: String): ContentTabs {
            for(c in values) {
                if(c.displayName == name) {
                    return c
                }
            }
            return NONE
        }

        fun findByTabId(tabId: Int) = values[tabId]

    }

}
