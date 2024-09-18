package org.xephyrous.com.UI

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Add
import androidx.compose.material.icons.sharp.PlayArrow
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.*
import kotlinx.coroutines.*
import org.xephyrous.com.ChatBox
import org.xephyrous.com.Utils.Global
import org.xephyrous.com.Utils.textHeightFix
import org.xephyrous.com.Utils.textSpacingFix
import androidx.compose.material.Icon as Icon1

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
        while(true) {
            if (!Global.initialized) {
                delay(200)
            } else { break }
        }

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
            modifier = Modifier.offset(bottomOffset.x.dp, bottomOffset.y.textHeightFix(fontSize)),
            style = LocalTextStyle.current.copy(
                color = Color(0xFF53212B),
                fontSize = fontSize.value.sp,
                fontFamily = ValorantFont()
            )
        )

        Text(
            "VALIANCE",
            modifier = Modifier.offset(0.dp, (0).textHeightFix(fontSize)),
            style = LocalTextStyle.current.copy(
                color = Color(0xFFBD3944),
                fontSize = fontSize.value.sp,
                fontFamily = ValorantFont()
            )
        )

        Text(
            "VALIANCE",
            modifier = Modifier.offset(topOffset.x.dp, topOffset.y.textHeightFix(fontSize)),
            style = LocalTextStyle.current.copy(
                color = Color(0xFFFD4556),
                fontSize = fontSize.value.sp,
                fontFamily = ValorantFont()
            )
        )
    }
}

@Composable
fun UserChatField() {
    Box(
        modifier = Modifier.fillMaxWidth().fillMaxHeight(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(.4F).align(Alignment.Center)
        ) {
            Spacer(modifier = Modifier.fillMaxHeight(.1F))
            Box(
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(15.dp, 15.dp, 15.dp, 15.dp))
                    .fillMaxHeight(.90F)
                    .background(Color(0xFF141414))
            ) {
                val lazyListState = rememberLazyListState()
                LazyColumn(
                    state = lazyListState,
                    modifier = Modifier.fillMaxSize().padding(5.dp)
                ){
                    items(Global.loadedMessages.size) { item ->
                        Global.loadedMessages[item].createBox()
                    }
                }
            }
            Spacer(modifier = Modifier.fillMaxHeight(.1F))
            Row {
                var input by rememberSaveable { mutableStateOf("") }

                Box(modifier = Modifier
                    .clip(shape = RoundedCornerShape(15.dp, 15.dp, 15.dp, 15.dp))
                    .fillMaxWidth(.9F)
                    .fillMaxHeight(.75F)
                    .background(Color(0xFF141414))
                ) {
                    TextField(
                        modifier = Modifier.fillMaxSize(),
                        value = input,
                        onValueChange = { newText ->
                            input = newText
                        },
                        colors = TextFieldDefaults.textFieldColors(
                            cursorColor = Color(0xFFFD4556),
                            focusedIndicatorColor = Color(0xFFFD4556),
                        ),
                        singleLine = true,
                        placeholder = { Text("INPUT TEXT HERE", color = Color(0x15FFFFFF), fontSize = 25.sp, fontFamily = TungstenFont()) },
                        textStyle = TextStyle(
                            fontFamily = TungstenFont(),
                            fontSize = 25.sp,
                            color = Color.White
                        )
                    )
                }
                Spacer(Modifier.fillMaxWidth(.15F))
                Box(
                    modifier = Modifier
                        .clip(shape = RoundedCornerShape(15.dp, 15.dp, 15.dp, 15.dp))
                        .fillMaxWidth()
                        .fillMaxHeight(.75F)
                        .background(Color(0xFF141414))
                ) {
                    IconButton(
                        onClick = {
                            if (input.isNotEmpty()) {
                                // Upload the message to the screen
                                val temp: ArrayList<ChatBox> = arrayListOf()
                                temp.addAll(Global.loadedMessages)
                                temp.add(ChatBox(true, input))
                                Global.loadedMessages = temp
                                input = ""
                                // Get response
                                temp.add(ChatBox(false, "Pretend this is text that the LLM has responded to you with!"))
                                Global.loadedMessages = temp
                            } else {
                                //crash their application or smth idk
                            }
                        }
                    ) {
                        Icon1(
                            modifier = Modifier.fillMaxSize(),
                            imageVector = Icons.Sharp.PlayArrow,
                            contentDescription = "Sned massage",
                            tint = Color(0xFFFD4556)
                        )
                    }
                }
            }
        }
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
        while(true) {
            if (!Global.initialized) {
                delay(200)
            } else { break }
        }

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
                modifier = Modifier
                    .align(Alignment.Center)
                    .offset((0).textSpacingFix(10.sp), 0.dp),
                letterSpacing = 10.sp,
                style = LocalTextStyle.current.copy(
                    fontFamily = TungstenFont(),
                    fontSize = fontSize.value.sp
                )
            )
        }
    }
}