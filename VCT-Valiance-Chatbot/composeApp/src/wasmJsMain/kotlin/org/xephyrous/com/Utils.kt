package org.xephyrous.com

import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.*

/**
 * ***< THIS IS A HACK! >***
 * Correctly offsets text to center it regardless of the bottom spacing
 * Because for some reason compose has NOTHING to remedy this
 */
@Composable
fun Int.textFix(textSize: Dp) : Dp {
    return (this + textSize.value.toInt() / 8).dp
}

/**
 * Contains global information for use in the application
 */
object Global {
    var viewSize: DpSize = DpSize(0.dp, 0.dp)
}
