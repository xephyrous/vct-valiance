package org.xephyrous.com.Utils

import org.xephyrous.com.JSInterop.*

enum class TeamDataTheme {
    BORDER_COLOR,
    BG_COLOR,
    ACCENT_COLOR,
    HEADER_TEXT,
    PLAYER_TEXT
}

/**
 * Digests the response from digestTeam() into individual data members
 * @param rawString The model's response string containing the team data as JSON
 * @param pureJSON Whether the string is pure JSON, or a full model response string
 */
@Suppress("UNCHECKED_CAST")
class TeamData {
    lateinit var name: String
    lateinit var igl: String
    lateinit var coach: String
    lateinit var members: Array<String>
    lateinit var roles: Array<String>
    lateinit var agents: Array<String>
    lateinit var theme: Array<Long>

    fun loadJSON(rawString: String, pureJSON: Boolean) {
        try {
            val json = Tools.teamDataToJSON(rawString, pureJSON).onSuccess {
                Tools.cacheJSON(it)
            }.onFailure {
                // TODO("Invalid model response UI alert")
                // Handle this somehow?
            }

            this.name = Tools.extractJSONObject("name", String::toString)
            this.igl = Tools.extractJSONObject("igl", String::toString)
            this.coach = Tools.extractJSONObject("coach", String::toString)
            this.members = Tools.extractJSONArray("members", String::toString)
            this.roles = Tools.extractJSONArray("roles", String::toString)
            this.agents = Tools.extractJSONArray("agents", String::toString)
            this.theme = Tools.extractJSONArray("theme", String::toLong)

            Tools.clearJSONCache()
        } catch (e: Exception) {
            JSFirebase.debug(e.stackTraceToString())
            /* Shit man, idk ＼（〇_ｏ）／ */
        }
    }
}