package org.xephyrous.com.Utils

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import org.xephyrous.com.ChatBox

/**
 * Contains global information for use in the application
 */
object Global {
    var viewSize: DpSize = DpSize(0.dp, 0.dp)

    var loadedMessages: ArrayList<ChatBox> by mutableStateOf(arrayListOf())

    var sendingMessage by mutableStateOf(false)

    var createdTeams: Array<String> by mutableStateOf(arrayOf<String>())
    var createdUUIDS: Array<String> by mutableStateOf(arrayOf<String>())

    var selectedTeam: TeamData? by mutableStateOf(null)
    var displayingTeam by mutableStateOf(false)
    var menuOpened by mutableStateOf(false)
    var selectingTeam by mutableStateOf(false)

    var sessionUUID: String? = null
    var initialized: Boolean by mutableStateOf(false)
    var initializing: Boolean by mutableStateOf(false)
}
