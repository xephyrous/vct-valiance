package org.xephyrous.com.Utils

import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp

/**
 * Contains global information for use in the application
 */
object Global {
    var viewSize: DpSize = DpSize(0.dp, 0.dp)
    var sessionUUID: String? = null
    var initialized: Boolean = false
}
