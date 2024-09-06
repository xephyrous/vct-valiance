package org.xephyrous.com

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp

@Composable
fun pxToDp(value: Int) : Dp {
    return with (LocalDensity.current) { value.toDp() }
}
