package org.xephyrous.com

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.DpSize
import org.xephyrous.com.Utils.Global
import org.xephyrous.com.UI.ExpandableChatBackground
import org.xephyrous.com.UI.VCTBlockText
import org.xephyrous.com.UI.Valiance

@Composable
fun App() {
    MaterialTheme {
        BoxWithConstraints(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF1E1E1E)),
        ) {
            // Setup global size
            Global.viewSize = DpSize(maxWidth, maxHeight)

            //Splash screen
            ExpandableChatBackground()
            VCTBlockText()
            Valiance()
        }
    }
}