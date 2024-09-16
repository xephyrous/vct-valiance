package org.xephyrous.com.Utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.*

/**
 * ***< THIS IS A HACK! >***
 * Correctly offsets text to center it regardless of the bottom spacing
 * Because for some reason compose has NOTHING to remedy this
 */
@Composable
fun Int.textHeightFix(textSize: Dp) : Dp {
    return (this + textSize.value.toInt() / 8).dp
}

/**
 * ***< THIS IS A HACK! >***
 * Removes extraneous letter spacing following text
 * Because compose places custom letter spacing after the text too >:(
 */
@Composable
fun Int.textSpacingFix(spacing: TextUnit) : Dp {
    return (this + (spacing.value.sp / 2).value.toInt()).dp
}