package org.xephyrous.com.Utils

import org.xephyrous.com.JSInterop.*
import org.xephyrous.com.UI.Alerts

/**
 * Digests the response from digestTeam() into individual data members
 */
class TeamData {
    var name: String = "Default Name"
    var uuid: String = "0"
    var igl: String = "Default IGL"
    var coach: String = "Default Coach"
    lateinit var members: Array<String>
    lateinit var roles: Array<String>
    lateinit var agents: Array<String>
    val theme = ArrayList<Long>()

    init {
        theme.add(4279505940) // Border
        theme.add(4279505940) // Background
        theme.add(4290591044) // Accent
        theme.add(4294967295) // Header
        theme.add(4294967295) // Player
    }

    fun loadJSON(rawString: String, pureJSON: Boolean) {
        try {
            Tools.teamDataToJSON(rawString, pureJSON).onSuccess {
                Tools.cacheJSON(it)
            }.onFailure {
                Alerts.displayAlert("Invalid model response, failed to load team object!")
                return
            }

            this.name = Tools.extractJSONObject("name", String::toString)
            this.uuid = Tools.extractJSONObject("uuid", String::toString)
            this.igl = Tools.extractJSONObject("igl", String::toString)
            this.coach = Tools.extractJSONObject("coach", String::toString)
            this.members = Tools.extractJSONArray("members", String::toString)
            this.roles = Tools.extractJSONArray("roles", String::toString)
            this.agents = Tools.extractJSONArray("agents", String::toString)

            Tools.clearJSONCache()
        } catch (e: Exception) {
            JSFirebase.debug(e.stackTraceToString())
            /* Shit man, idk ＼（〇_ｏ）／ */
        }
    }
}