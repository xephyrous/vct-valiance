package org.xephyrous.com

import androidx.compose.animation.core.EaseInOut
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateIntOffsetAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.onClick
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.*

@OptIn(ExperimentalFoundationApi::class)
@Composable()
fun Valiance() {
    var targetPos by remember { mutableStateOf(IntOffset(0, 75)) }
    var targetFontSize by remember { mutableStateOf(40.sp) }

    val offset by animateIntOffsetAsState(
        targetValue = targetPos,
        animationSpec = tween(
            durationMillis = 1000,
            easing = EaseInOut
        )
    )

    val fontSize by animateDpAsState(
        targetValue = with (LocalDensity.current) { targetFontSize.toDp() },
        animationSpec = tween(
            durationMillis = 1000,
            easing = EaseInOut
        )
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .offset { offset }
            .onClick { targetPos = IntOffset(0, -400) },
        contentAlignment = Alignment.Center
    ) {
        Text(
            "VALIANCE",
            Modifier.wrapContentHeight(Alignment.CenterVertically).offset(x = (-10).dp, y = 10.dp),
            style = LocalTextStyle.current.copy(
                color = Color(0xFF53212B),
                textAlign = TextAlign.Center,
                fontSize = 330.0.sp,
                fontFamily = ValorantFont(),
            )
        )

        Text(
            "VALIANCE",
            Modifier.wrapContentHeight(Alignment.CenterVertically),
            style = LocalTextStyle.current.copy(
                color = Color(0xFFBD3944),
                textAlign = TextAlign.Center,
                fontSize = 300.0.sp,
                fontFamily = ValorantFont(),
            )
        )

        Text(
            "VALIANCE",
            Modifier.wrapContentHeight(Alignment.CenterVertically)
                    .offset(x = 10.dp, y = (-10).dp),
            style = LocalTextStyle.current.copy(
                color = Color(0xFFFD4556),
                textAlign = TextAlign.Center,
                fontSize = 300.0.sp,
                fontFamily = ValorantFont()
            )
        )
    }
}

@Composable
fun ExpandableChatBackground() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .clip(shape = RoundedCornerShape(15.dp, 15.dp, 15.dp, 15.dp))
                .size(1600.dp, 650.dp)
                .background(Color(0xFF141414))
        )
    }
}

@Composable
fun VCTBlockText() {
    Box(
        modifier = Modifier
            .size(100.dp, 45.dp)
            .background(Color(0xFFBD3944)),
        contentAlignment = Alignment.Center
    ) {
        Text(
            "VCT",
            style = LocalTextStyle.current.copy(
                letterSpacing = TextUnit(10f, TextUnitType.Sp),
                fontFamily = TungstenFont()
            )
        )
    }
}