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

    val selectedTeam by mutableStateOf(TeamData())
    var displayingTeam by mutableStateOf(false)

    var sessionUUID: String? = null
    var initialized: Boolean by mutableStateOf(false)
    var initializing: Boolean by mutableStateOf(false)
}
