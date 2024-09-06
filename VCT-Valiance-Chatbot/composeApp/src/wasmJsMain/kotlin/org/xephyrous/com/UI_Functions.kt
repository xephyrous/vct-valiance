package org.xephyrous.com

import androidx.compose.foundation.layout.*
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable()
fun Valiance() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            "VALIANCE",
            Modifier.wrapContentHeight(Alignment.CenterVertically).offset(x = (-10).dp, y = 10.dp),
            style = LocalTextStyle.current.copy(
                color = Color(0.33f, 0.13f, 0.17f, 1.0f),
                textAlign = TextAlign.Center, fontSize = 300.0.sp,
                fontFamily = ValorantFont()
            )
        )

        Text(
            "VALIANCE",
            Modifier.wrapContentHeight(Alignment.CenterVertically),
            style = LocalTextStyle.current.copy(
                color = Color(0.74f, 0.22f, 0.27f, 1.0f),
                textAlign = TextAlign.Center, fontSize = 300.0.sp,
                fontFamily = ValorantFont()
            )
        )

        Text(
            "VALIANCE",
            Modifier.wrapContentHeight(Alignment.CenterVertically)
                    .offset(x = 10.dp, y = (-10).dp),
            style = LocalTextStyle.current.copy(
                color = Color(0.99f, 0.27f, 0.34f, 1.0f),
                textAlign = TextAlign.Center, fontSize = 300.0.sp,
                fontFamily = ValorantFont()
            )
        )
    }
}