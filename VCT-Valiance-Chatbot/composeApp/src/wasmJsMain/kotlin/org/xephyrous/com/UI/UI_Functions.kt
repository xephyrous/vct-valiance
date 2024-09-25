package org.xephyrous.com.UI

import androidx.compose.animation.*
import androidx.compose.animation.core.EaseInOut
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onPreviewKeyEvent
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.node.ParentDataModifierNode
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.toLowerCase
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.*
import org.jetbrains.compose.resources.painterResource
import org.xephyrous.com.JSInterop.BedrockRuntime
import org.xephyrous.com.Utils.ErrorType.MODEL_RESPONSE
import org.xephyrous.com.Utils.Global
import org.xephyrous.com.Utils.agentMap
import org.xephyrous.com.Utils.awaitHandled
import org.xephyrous.com.Utils.updateText
import vctvaliancechatbot.composeapp.generated.resources.*
import vctvaliancechatbot.composeapp.generated.resources.Res
import vctvaliancechatbot.composeapp.generated.resources.VCT_Block
import vctvaliancechatbot.composeapp.generated.resources.Valiance

/**
 *
 */
@Composable
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
        val padding by animateFloatAsState(
            targetValue = if (Global.initialized) .05f else .35F,
            animationSpec = tween(durationMillis = 1000, easing = EaseInOut)
        )
        Column(
            modifier = Modifier.fillMaxWidth(width).fillMaxHeight(height).align(Alignment.TopCenter)
        ) {
            Spacer(Modifier.fillMaxHeight(padding))
            Image(
                painter = painterResource(resource = Res.drawable.VCT_Block),
                contentDescription = "",
                modifier = Modifier.fillMaxHeight(0.2F).fillMaxWidth(),
                alignment = Alignment.BottomCenter,
                contentScale = ContentScale.Fit
            )
            Image(
                painter = painterResource(resource = Res.drawable.Valiance),
                contentDescription = "",
                modifier = Modifier.fillMaxSize(),
                alignment = Alignment.TopCenter,
                contentScale = ContentScale.Fit
            )
        }
    }
}

@OptIn(DelicateCoroutinesApi::class)
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
                        enabled = Global.initialized && !Global.sendingMessage,
                        value = input,
                        keyboardOptions = KeyboardOptions.Default.copy(
                            imeAction = ImeAction.Done
                        ),
                        keyboardActions = KeyboardActions(onDone = {
                            if (input.isNotEmpty() || Global.sendingMessage) {
                                Global.sendingMessage = true

                                val tempText = input
                                input = ""

                                // Upload the message to the screen
                                updateText(true, tempText)

                                // Send query and wait for response
                                GlobalScope.launch(Dispatchers.Default) {
                                    BedrockRuntime.InvokeModel(tempText).onFailure {
                                        this.cancel("Model failed to load response!")
                                        // TODO("Model failure UI alert")
                                    }.onSuccess { updateText(false, it) }
                                    Global.sendingMessage = false
                                }
                            }
                        }),
                        onValueChange = { newText ->
                            input = newText
                        },
                        colors = TextFieldDefaults.textFieldColors(
                            cursorColor = Color(0xFFFD4556),
                            focusedIndicatorColor = Color(0xFFFD4556),
                        ),
                        singleLine = true,
                        placeholder = { Text("Ask Away!", color = Color(0x15FFFFFF), fontSize = 25.sp, fontFamily = FFMarkFont()) },
                        textStyle = TextStyle(
                            fontFamily = FFMarkFont(),
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
                            if (input.isNotEmpty() || Global.sendingMessage) {
                                Global.sendingMessage = true

                                val tempText = input
                                input = ""

                                // Upload the message to the screen
                                updateText(true, tempText)

                                val temp = input
                                input = ""

                                // Send query and wait for response
                                GlobalScope.launch(Dispatchers.Default) {
                                    BedrockRuntime.InvokeModel(tempText).onFailure {
                                        this.cancel("Model failed to load response!")
                                        // TODO("Model failure UI alert")
                                    }.onSuccess { updateText(false, it) }
                                    Global.sendingMessage = false
                                }
                            }
                        }
                    ) {
                        Icon(
                            modifier = Modifier.fillMaxSize(),
                            imageVector = Icons.Sharp.PlayArrow,
                            contentDescription = "Send massage",
                            tint = Color(0xFFFD4556)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun TeamSelect() {
    Box(
        modifier = Modifier.fillMaxWidth().fillMaxHeight()
    ) {
        Button(
            onClick = {
                Global.displayingTeam = !Global.displayingTeam
            },
            modifier = Modifier.size(250.dp, 250.dp)
        ) {
            Text("this is a temp button, if you are seeing this the ui designer forgot to remove it :D")
        }
    }
}

@Composable
fun TeamDisplay() {
    Box(
        modifier = Modifier.fillMaxWidth().fillMaxHeight()
    ) {
        Box(
            modifier = Modifier.fillMaxWidth(.25F).fillMaxHeight(.8F).align(Alignment.CenterEnd),
        ) {
            AnimatedVisibility (
                visible = Global.displayingTeam && Global.initialized,
                enter = fadeIn(
                    animationSpec = tween(250)
                ),
                exit = fadeOut(
                    animationSpec = tween(250)
                )
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth().fillMaxHeight(),
                ) {
                    Spacer(modifier = Modifier.fillMaxHeight(.1F))
                    Row(
                        modifier = Modifier.fillMaxWidth().fillMaxHeight(.1F),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Spacer(modifier = Modifier.fillMaxWidth(.05F))
                        Box(
                            modifier = Modifier
                                .fillMaxWidth(.3F)
                                .fillMaxHeight(.7F)
                                .background(Color(Global.selectedTeam.theme[1])),
                        ) {
                            Text(
                                text = Global.selectedTeam.name,
                                modifier = Modifier
                                    .align(Alignment.Center),
                                color = Color(Global.selectedTeam.theme[3]),
                                fontFamily = TungstenFont(),
                                fontSize = 25.sp
                            )
                        }
                        Spacer(modifier = Modifier.fillMaxWidth(.05F))
                        Box(
                            modifier = Modifier
                                .fillMaxWidth(.3F)
                                .fillMaxHeight(.05F)
                                .background(Color(Global.selectedTeam.theme[2]))
                        )
                        Spacer(modifier = Modifier.fillMaxWidth(.09F))
                        Text(
                            text = "Coach: " + Global.selectedTeam.coach,
                            color = Color(Global.selectedTeam.theme[3]),
                            fontFamily = TungstenFont(),
                            fontSize = 25.sp
                        )
                    }
                }
                Column(
                    modifier = Modifier.fillMaxWidth().fillMaxHeight()
                ) {
                    Spacer(modifier = Modifier.fillMaxHeight(.205F))
                    Column(
                        modifier = Modifier.fillMaxWidth().fillMaxHeight(.1F),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth(.7F)
                                .fillMaxHeight(.05F)
                                .background(Color(Global.selectedTeam.theme[2]))
                        )
                    }
                }
                Column(
                    modifier = Modifier.fillMaxWidth().fillMaxHeight(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Spacer(modifier = Modifier.fillMaxHeight(.25F))
                    Row(
                        modifier = Modifier.fillMaxWidth(.9F).fillMaxHeight(.15F)
                            .background(Color(Global.selectedTeam.theme[1]))
                            .border(BorderStroke(2.dp, Color(Global.selectedTeam.theme[0]))),
                        horizontalArrangement = Arrangement.Center,
                    ) {
                        Box(
                            modifier = Modifier.fillMaxHeight().fillMaxWidth(.22F).background(Color(Global.selectedTeam.theme[0])),
                        ) {
                            Image(
                                painter = painterResource(resource = agentMap[Global.selectedTeam.agents[0].lowercase()]?.get(0) ?: Res.drawable.VCT_Block),
                                contentDescription = "",
                                modifier = Modifier.fillMaxSize().padding(5.dp),
                                alignment = Alignment.Center,
                                contentScale = ContentScale.Fit
                            )
                        }
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(.70F)
                                .fillMaxHeight(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = Global.selectedTeam.members[0],
                                color = Color(Global.selectedTeam.theme[4]),
                                fontFamily = TungstenFont(),
                                fontSize = 45.sp
                            )
                            if(Global.selectedTeam.members[0] == Global.selectedTeam.igl) {
                                Image(
                                    painter = painterResource(resource = Res.drawable.IGL),
                                    contentDescription = "",
                                    contentScale = ContentScale.Fit
                                )
                            }
                        }
                        Box(
                            modifier = Modifier.fillMaxSize()
                        ) {
                            Image(
                                painter = painterResource(resource = agentMap[Global.selectedTeam.agents[0].lowercase()]?.get(1) ?: Res.drawable.VCT_Block),
                                contentDescription = "",
                                modifier = Modifier.fillMaxHeight().scale(-1F, 1F),
                                alignment = Alignment.Center,
                                contentScale = ContentScale.Fit
                            )
                        }
                    }
                }
                Column(
                    modifier = Modifier.fillMaxWidth().fillMaxHeight(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Spacer(modifier = Modifier.fillMaxHeight(.4F))
                    Row(
                        modifier = Modifier.fillMaxWidth(.9F).fillMaxHeight(.1875F)
                            .background(Color(Global.selectedTeam.theme[1]))
                            .border(BorderStroke(2.dp, Color(Global.selectedTeam.theme[0]))),
                        horizontalArrangement = Arrangement.Center,
                    ) {
                        Box(
                            modifier = Modifier.fillMaxHeight().fillMaxWidth(.22F).background(Color(Global.selectedTeam.theme[0])),
                        ) {
                            Image(
                                painter = painterResource(resource = agentMap[Global.selectedTeam.agents[1].lowercase()]?.get(0) ?: Res.drawable.VCT_Block),
                                contentDescription = "",
                                modifier = Modifier.fillMaxSize().padding(5.dp),
                                alignment = Alignment.Center,
                                contentScale = ContentScale.Fit
                            )
                        }
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(.70F)
                                .fillMaxHeight(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = Global.selectedTeam.members[1],
                                color = Color(Global.selectedTeam.theme[4]),
                                fontFamily = TungstenFont(),
                                fontSize = 45.sp
                            )
                            if(Global.selectedTeam.members[1] == Global.selectedTeam.igl) {
                                Image(
                                    painter = painterResource(resource = Res.drawable.IGL),
                                    contentDescription = "",
                                    contentScale = ContentScale.Fit
                                )
                            }
                        }
                        Box(
                            modifier = Modifier.fillMaxSize()
                        ) {
                            Image(
                                painter = painterResource(resource = agentMap[Global.selectedTeam.agents[1].lowercase()]?.get(1) ?: Res.drawable.VCT_Block),
                                contentDescription = "",
                                modifier = Modifier.fillMaxHeight().scale(-1F, 1F),
                                alignment = Alignment.Center,
                                contentScale = ContentScale.Fit
                            )
                        }
                    }
                }
                Column(
                    modifier = Modifier.fillMaxWidth().fillMaxHeight(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Spacer(modifier = Modifier.fillMaxHeight(.55F))
                    Row(
                        modifier = Modifier.fillMaxWidth(.9F).fillMaxHeight(.25F)
                            .background(Color(Global.selectedTeam.theme[1]))
                            .border(BorderStroke(2.dp, Color(Global.selectedTeam.theme[0]))),
                        horizontalArrangement = Arrangement.Center,
                    ) {
                        Box(
                            modifier = Modifier.fillMaxHeight().fillMaxWidth(.22F).background(Color(Global.selectedTeam.theme[0])),
                        ) {
                            Image(
                                painter = painterResource(resource = agentMap[Global.selectedTeam.agents[2].lowercase()]?.get(0) ?: Res.drawable.VCT_Block),
                                contentDescription = "",
                                modifier = Modifier.fillMaxSize().padding(5.dp),
                                alignment = Alignment.Center,
                                contentScale = ContentScale.Fit
                            )
                        }
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(.70F)
                                .fillMaxHeight(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = Global.selectedTeam.members[2],
                                color = Color(Global.selectedTeam.theme[4]),
                                fontFamily = TungstenFont(),
                                fontSize = 45.sp
                            )
                            if(Global.selectedTeam.members[2] == Global.selectedTeam.igl) {
                                Image(
                                    painter = painterResource(resource = Res.drawable.IGL),
                                    contentDescription = "",
                                    contentScale = ContentScale.Fit
                                )
                            }
                        }
                        Box(
                            modifier = Modifier.fillMaxSize()
                        ) {
                            Image(
                                painter = painterResource(resource = agentMap[Global.selectedTeam.agents[2].lowercase()]?.get(1) ?: Res.drawable.VCT_Block),
                                contentDescription = "",
                                modifier = Modifier.fillMaxHeight().scale(-1F, 1F),
                                alignment = Alignment.Center,
                                contentScale = ContentScale.Fit
                            )
                        }
                    }
                }
                Column(
                    modifier = Modifier.fillMaxWidth().fillMaxHeight(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Spacer(modifier = Modifier.fillMaxHeight(.7F))
                    Row(
                        modifier = Modifier.fillMaxWidth(.9F).fillMaxHeight(.375F)
                            .background(Color(Global.selectedTeam.theme[1]))
                            .border(BorderStroke(2.dp, Color(Global.selectedTeam.theme[0]))),
                        horizontalArrangement = Arrangement.Center,
                    ) {
                        Box(
                            modifier = Modifier.fillMaxHeight().fillMaxWidth(.22F).background(Color(Global.selectedTeam.theme[0])),
                        ) {
                            Image(
                                painter = painterResource(resource = agentMap[Global.selectedTeam.agents[3].lowercase()]?.get(0) ?: Res.drawable.VCT_Block),
                                contentDescription = "",
                                modifier = Modifier.fillMaxSize().padding(5.dp),
                                alignment = Alignment.Center,
                                contentScale = ContentScale.Fit
                            )
                        }
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(.70F)
                                .fillMaxHeight(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = Global.selectedTeam.members[3],
                                color = Color(Global.selectedTeam.theme[4]),
                                fontFamily = TungstenFont(),
                                fontSize = 45.sp
                            )
                            if(Global.selectedTeam.members[3] == Global.selectedTeam.igl) {
                                Image(
                                    painter = painterResource(resource = Res.drawable.IGL),
                                    contentDescription = "",
                                    contentScale = ContentScale.Fit
                                )
                            }
                        }
                        Box(
                            modifier = Modifier.fillMaxSize()
                        ) {
                            Image(
                                painter = painterResource(resource = agentMap[Global.selectedTeam.agents[3].lowercase()]?.get(1) ?: Res.drawable.VCT_Block),
                                contentDescription = "",
                                modifier = Modifier.fillMaxHeight().scale(-1F, 1F),
                                alignment = Alignment.Center,
                                contentScale = ContentScale.Fit
                            )
                        }
                    }
                }
                Column(
                    modifier = Modifier.fillMaxWidth().fillMaxHeight(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Spacer(modifier = Modifier.fillMaxHeight(.85F))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(.9F)
                            .fillMaxHeight(.75F)
                            .background(Color(Global.selectedTeam.theme[1]))
                            .border(BorderStroke(2.dp, Color(Global.selectedTeam.theme[0]))),
                        horizontalArrangement = Arrangement.Center,
                    ) {
                        Box(
                            modifier = Modifier.fillMaxHeight().fillMaxWidth(.22F).background(Color(Global.selectedTeam.theme[0])),
                        ) {
                            Image(
                                painter = painterResource(resource = agentMap[Global.selectedTeam.agents[4].lowercase()]?.get(0) ?: Res.drawable.VCT_Block),
                                contentDescription = "",
                                modifier = Modifier.fillMaxSize().padding(5.dp),
                                alignment = Alignment.Center,
                                contentScale = ContentScale.Fit
                            )
                        }
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(.70F)
                                .fillMaxHeight(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = Global.selectedTeam.members[4],
                                color = Color(Global.selectedTeam.theme[4]),
                                fontFamily = TungstenFont(),
                                fontSize = 45.sp
                            )
                            if(Global.selectedTeam.members[4] == Global.selectedTeam.igl) {
                                Image(
                                    painter = painterResource(resource = Res.drawable.IGL),
                                    contentDescription = "",
                                    contentScale = ContentScale.Fit
                                )
                            }
                        }
                        Box(
                            modifier = Modifier.fillMaxSize()
                        ) {
                            Image(
                                painter = painterResource(resource = agentMap[Global.selectedTeam.agents[4].lowercase()]?.get(1) ?: Res.drawable.VCT_Block),
                                contentDescription = "",
                                modifier = Modifier.fillMaxHeight().scale(-1F, 1F),
                                alignment = Alignment.Center,
                                contentScale = ContentScale.Fit
                            )
                        }
                    }
                }
            }
        }
    }
}