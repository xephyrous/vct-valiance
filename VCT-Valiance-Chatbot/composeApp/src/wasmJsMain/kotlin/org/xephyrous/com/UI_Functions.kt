package org.xephyrous.com

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.*
import kotlinx.coroutines.*

/**
 *
 */
@OptIn(DelicateCoroutinesApi::class)
@Composable()
fun Valiance() {
    var targetFontSize by remember { mutableStateOf((Global.viewSize.width / 6).value.sp) }
    var targetPos by remember { mutableStateOf(IntOffset(0, 0)) }

    var topOffsetState by remember { mutableStateOf(IntOffset(10, -10)) }
    var bottomOffsetState by remember { mutableStateOf(IntOffset(-10, 10)) }

    val offset by animateIntOffsetAsState(
        targetValue = targetPos,
        animationSpec = tween(durationMillis = 1000, easing = EaseInOut)
    )

    val topOffset by animateIntOffsetAsState(
        targetValue = topOffsetState,
        animationSpec = tween(durationMillis = 1000, easing = EaseInOut)
    )

    val bottomOffset by animateIntOffsetAsState(
        targetValue = bottomOffsetState,
        animationSpec = tween(durationMillis = 1000, easing = EaseInOut)
    )

    val fontSize by animateDpAsState(
        targetValue = with (LocalDensity.current) { targetFontSize.toDp() },
        animationSpec = tween(durationMillis = 1000, easing = EaseInOut)
    )

    val logoOffset = with (LocalDensity.current) { -(Global.viewSize.height / 2.6f).toPx() }

    GlobalScope.launch(Dispatchers.Default) {
        delay(1200)
        targetPos = IntOffset(0, logoOffset.toInt())
        targetFontSize = 100.sp
        topOffsetState = IntOffset(5, -5)
        bottomOffsetState = IntOffset(-5, 5)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .offset { offset },
        contentAlignment = Alignment.Center
    ) {
        Text(
            "VALIANCE",
            modifier = Modifier.offset(bottomOffset.x.dp, bottomOffset.y.textFix(fontSize)),
            style = LocalTextStyle.current.copy(
                color = Color(0xFF53212B),
                fontSize = fontSize.value.sp,
                fontFamily = ValorantFont()
            )
        )

        Text(
            "VALIANCE",
            modifier = Modifier.offset(0.dp, (0).textFix(fontSize)),
            style = LocalTextStyle.current.copy(
                color = Color(0xFFBD3944),
                fontSize = fontSize.value.sp,
                fontFamily = ValorantFont()
            )
        )

        Text(
            "VALIANCE",
            modifier = Modifier.offset(topOffset.x.dp, topOffset.y.textFix(fontSize)),
            style = LocalTextStyle.current.copy(
                color = Color(0xFFFD4556),
                fontSize = fontSize.value.sp,
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
                .size(1600.dp, 600.dp)
                .background(Color(0xFF141414))
        )
    }
}

@OptIn(DelicateCoroutinesApi::class)
@Composable
fun VCTBlockText() {
    val fontSizeV = with (LocalDensity.current) { (Global.viewSize.width / 6).toPx() }
    val offset = with (LocalDensity.current) { -(Global.viewSize.height / 2.15f).toPx() }

    var offsetPosState by remember { mutableStateOf(IntOffset(0, -(fontSizeV / 1.6f).toInt())) }
    var boxSizeState by remember { mutableStateOf(Size(150f, 75f)) }
    var fontSizeState by remember { mutableStateOf(60.sp) }

    val offsetPos by animateIntOffsetAsState(
        targetValue = offsetPosState,
        animationSpec = tween(durationMillis = 1000, easing = EaseInOut)
    )

    val boxSize by animateSizeAsState(
        targetValue = boxSizeState,
        animationSpec = tween(durationMillis = 1000, easing = EaseInOut)
    )

    val fontSize by animateDpAsState(
        targetValue = with (LocalDensity.current) { fontSizeState.toDp() },
        animationSpec = tween(durationMillis = 1000, easing = EaseInOut)
    )

    GlobalScope.launch(Dispatchers.Default) {
        delay(1200)
        offsetPosState = IntOffset(0, offset.toInt())
        fontSizeState = 30.sp
        boxSizeState = Size(80f, 38f)
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .offset { offsetPos }
                .size(boxSize.width.dp, boxSize.height.dp)
                .background(Color(0xFFBD3944)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                "VCT",
                style = LocalTextStyle.current.copy(
                    letterSpacing = 5.sp,
                    fontFamily = FFMarkFont(),
                    fontSize = fontSize.value.sp
                )
            )
        }
    }
}