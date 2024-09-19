package org.xephyrous.com.UI

import androidx.compose.animation.core.EaseInOut
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.painterResource
import org.xephyrous.com.Utils.Global
import org.xephyrous.com.Utils.updateText
import vctvaliancechatbot.composeapp.generated.resources.Res
import vctvaliancechatbot.composeapp.generated.resources.logo

/**
 *
 */
@Composable()
fun Valiance() {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        val width by animateFloatAsState(
            targetValue = if (Global.initialized) .4f else .8F,
            animationSpec = tween(durationMillis = 1000, easing = EaseInOut)
        )
        val height by animateFloatAsState(
            targetValue = if (Global.initialized) .1f else 1F,
            animationSpec = tween(durationMillis = 1000, easing = EaseInOut)
        )
        Column(
            modifier = Modifier.fillMaxWidth(width).fillMaxHeight(height).align(Alignment.TopCenter)
        ) {
            Spacer(Modifier.fillMaxHeight(.05F))
            Image(
                painter = painterResource(resource = Res.drawable.logo),
                contentDescription = "",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Fit
            )
        }
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
                        enabled = Global.initialized,
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
                                updateText(true, input)
                                input = ""
                                // Get response
                                // Placeholder Text
                                updateText(false, "Pretend this is text that the LLM has responded to you with!")
                            } else {
                                //crash their application or smth IDK
                            }
                        }
                    ) {
                        Icon(
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