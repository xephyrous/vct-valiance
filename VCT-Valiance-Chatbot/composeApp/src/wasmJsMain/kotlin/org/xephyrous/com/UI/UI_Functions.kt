package org.xephyrous.com.UI

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.EaseInOut
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
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
import androidx.compose.material.icons.sharp.Close
import androidx.compose.material.icons.sharp.PlayArrow
import androidx.compose.material.icons.sharp.Settings
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.*
import org.jetbrains.compose.resources.painterResource
import org.xephyrous.com.JSInterop.Firebase
import org.xephyrous.com.JSInterop.JSFirebase
import org.xephyrous.com.Utils.Global
import org.xephyrous.com.Utils.agentMap
import org.xephyrous.com.Utils.sendMessage
import vctvaliancechatbot.composeapp.generated.resources.IGL
import vctvaliancechatbot.composeapp.generated.resources.Res
import vctvaliancechatbot.composeapp.generated.resources.VCT_Block
import vctvaliancechatbot.composeapp.generated.resources.Valiance

@OptIn(DelicateCoroutinesApi::class)
@Composable
fun Settings() {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        AnimatedVisibility (
            visible = Global.initialized,
            enter = fadeIn(
                animationSpec = tween(250)
            ),
            exit = fadeOut(
                animationSpec = tween(250)
            )
        ) {
            IconButton(
                onClick = {
                    Global.menuOpened = !Global.menuOpened
                }
            ) {
                Icon(
                    imageVector = if (Global.menuOpened) Icons.Sharp.Close else Icons.Sharp.Settings,
                    contentDescription = "Settings",
                    tint = Color(0xFFFD4556)
                )
            }
            val open by animateFloatAsState(
                targetValue = if (Global.menuOpened) .05f else 1F,
                animationSpec = tween(durationMillis = 300, easing = EaseInOut)
            )
            Column(
                modifier = Modifier.fillMaxHeight().fillMaxWidth(.225F)
            ) {
                Spacer(
                    modifier = Modifier.fillMaxHeight(open)
                )
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color(0xFF141414))
                        .clip(shape = RoundedCornerShape(0.dp, 15.dp, 0.dp, 0.dp))
                ) {
                    AnimatedVisibility ( // Main Settings Menu
                        visible = !Global.selectingTeam,
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
                            Spacer(modifier = Modifier.fillMaxHeight(.03F))
                            Text(
                                text = "Valiance Settings",
                                modifier = Modifier.align(Alignment.CenterHorizontally),
                                color = Color(0xFFFD4556),
                                fontFamily = TungstenFont(),
                                fontSize = 35.sp
                            )
                            Spacer(modifier = Modifier.fillMaxHeight(.03F))
                            Box(
                                modifier = Modifier.fillMaxWidth(.9F).fillMaxHeight(.003F).align(Alignment.CenterHorizontally).background(Color.Black)
                            )
                            Spacer(modifier = Modifier.fillMaxHeight(.032F))
                            Column(modifier = Modifier.fillMaxWidth().fillMaxHeight(.95F)) {
                                Box(
                                    modifier = Modifier.fillMaxWidth().fillMaxHeight(.05263158F)
                                ) {
                                    Button(
                                        onClick = {
                                            Global.selectingTeam = true
                                        },
                                        modifier = Modifier.align(Alignment.Center).fillMaxHeight().fillMaxWidth(.8F),
                                        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Black)
                                    ) {
                                        Text(
                                            text = "Select Custom Team",
                                            color = Color(0xFFFD4556),
                                            fontFamily = TungstenFont(),
                                            fontSize = 20.sp
                                        )
                                    }
                                }
                            }
                            Text(
                                text = Global.sessionUUID!!,
                                modifier = Modifier.align(Alignment.CenterHorizontally),
                                color = Color(0xFFFD4556),
                                fontFamily = TungstenFont(),
                                fontSize = 10.sp
                            )
                        }
                    }
                    AnimatedVisibility ( // Select Team
                        visible = Global.selectingTeam,
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
                            Spacer(modifier = Modifier.fillMaxHeight(.03F))
                            Text(
                                text = "Select Custom Team",
                                modifier = Modifier.align(Alignment.CenterHorizontally),
                                color = Color(0xFFFD4556),
                                fontFamily = TungstenFont(),
                                fontSize = 35.sp
                            )
                            Spacer(modifier = Modifier.fillMaxHeight(.03F))
                            Box(
                                modifier = Modifier.fillMaxWidth(.9F).fillMaxHeight(.003F).align(Alignment.CenterHorizontally).background(Color.Black)
                            )
                            Spacer(modifier = Modifier.fillMaxHeight(.032F))
                            val listState = rememberLazyListState()
                            LazyColumn(
                                state = listState,
                                modifier = Modifier.fillMaxWidth(.8F).fillMaxHeight(.75F).align(Alignment.CenterHorizontally)
                            ) {
                                items(Global.createdTeams.size) { item ->
                                    Box(
                                        modifier = Modifier.fillMaxWidth().fillMaxHeight(.25F)
                                    ) {
                                        Button(
                                            onClick = {
                                                GlobalScope.launch(Dispatchers.Default) {
                                                    Global.sendingMessage = true
                                                    Firebase.getTeamByUUID(Global.createdUUIDS[item])
                                                        .onSuccess {
                                                            team -> Global.selectedTeam = team
                                                            Global.displayingTeam = true
                                                        }
                                                        .onFailure { /* TODO("Failed to fetch teams list UI alert")  */ }
                                                    Global.sendingMessage = false
                                                }
                                            },
                                            modifier = Modifier.align(Alignment.Center).fillMaxHeight().fillMaxWidth(.8F),
                                            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Black)
                                        ) {
                                            Text(
                                                text = Global.createdTeams[item],
                                                color = Color(0xFFFD4556),
                                                fontFamily = TungstenFont(),
                                                fontSize = 20.sp
                                            )
                                        }
                                    }
                                }
                            }
                            Spacer(modifier = Modifier.fillMaxHeight(.2F))
                            Box(
                                modifier = Modifier.fillMaxWidth().fillMaxHeight(.25F)
                            ) {
                                Button(
                                    onClick = {
                                        if (Global.selectedTeam != null) { Global.displayingTeam = !Global.displayingTeam }
                                    },
                                    modifier = Modifier.align(Alignment.Center).fillMaxHeight().fillMaxWidth(.8F),
                                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.Black)
                                ) {
                                    Text(
                                        text = "Toggle Custom Team Display",
                                        color = Color(0xFFFD4556),
                                        fontFamily = TungstenFont(),
                                        fontSize = 20.sp
                                    )
                                }
                            }
                            Spacer(modifier = Modifier.fillMaxHeight(.1F))
                            Box(
                                modifier = Modifier.fillMaxWidth().fillMaxHeight(.37037F)
                            ) {
                                Button(
                                    onClick = {
                                        GlobalScope.launch(Dispatchers.Default) {
                                            Global.sendingMessage = true
                                            Firebase.getTeamNames()
                                                .onSuccess { result -> Global.createdTeams = result }
                                                .onFailure { TODO("Kill") }
                                            Firebase.getTeamUUIDs()
                                                .onSuccess { result -> Global.createdUUIDS = result }
                                                .onFailure { TODO("KILL AGAIN)") }
                                            Global.sendingMessage = false
                                        }
                                    },
                                    modifier = Modifier.align(Alignment.Center).fillMaxHeight().fillMaxWidth(.8F),
                                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.Black)
                                ) {
                                    Text(
                                        text = "Refresh Teams",
                                        color = Color(0xFFFD4556),
                                        fontFamily = TungstenFont(),
                                        fontSize = 20.sp
                                    )
                                }
                            }
                            Spacer(modifier = Modifier.fillMaxHeight(.17647F))
                            Box(
                                modifier = Modifier.fillMaxWidth().fillMaxHeight(.71429F)
                            ) {
                                Button(
                                    onClick = {
                                        Global.selectingTeam = false
                                    },
                                    modifier = Modifier.align(Alignment.Center).fillMaxHeight().fillMaxWidth(.8F),
                                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.Black)
                                ) {
                                    Text(
                                        text = "Return Home",
                                        color = Color(0xFFFD4556),
                                        fontFamily = TungstenFont(),
                                        fontSize = 20.sp
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

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
            targetValue = if (Global.initialized) .0025F else .26F,
            animationSpec = tween(durationMillis = 1000, easing = EaseInOut)
        )
        Column(
            modifier = Modifier.fillMaxWidth().fillMaxHeight().align(Alignment.TopCenter)
        ) {
            Spacer(Modifier.fillMaxHeight(padding))
            Box(
                modifier = Modifier.fillMaxWidth(width/10).fillMaxHeight(height/4).align(Alignment.CenterHorizontally)
            ) {
                Image(
                    painter = painterResource(resource = Res.drawable.VCT_Block),
                    contentDescription = "",
                    modifier = Modifier.fillMaxHeight().fillMaxWidth(),
                    alignment = Alignment.BottomCenter,
                    contentScale = ContentScale.Fit
                )
            }
            Box(
                modifier = Modifier.fillMaxWidth(width).fillMaxHeight(height).align(Alignment.CenterHorizontally),
            ) {
                Image(
                    painter = painterResource(resource = Res.drawable.Valiance),
                    contentDescription = "",
                    modifier = Modifier.fillMaxHeight().fillMaxWidth(),
                    alignment = Alignment.TopCenter,
                    contentScale = ContentScale.Fit
                )
            }
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
        val width by animateFloatAsState(
            targetValue = if (Global.initialized) .4f else .8F,
            animationSpec = tween(durationMillis = 1000, easing = EaseInOut)
        )
        val main by animateFloatAsState(
            targetValue = if (Global.initialized) .9f else .475F,
            animationSpec = tween(durationMillis = 500, easing = EaseInOut)
        )
        val secondary by animateFloatAsState(
            targetValue = if (Global.initialized) .1F else 0F,
            animationSpec = tween(durationMillis = 1000, easing = EaseInOut)
        )
        val auxiliary by animateFloatAsState(
            targetValue = if (Global.initialized) .75f else 0F,
            animationSpec = tween(durationMillis = 1000, easing = EaseInOut)
        )
        Column(
            modifier = Modifier.fillMaxWidth(width).align(Alignment.Center)
        ) {
            Spacer(modifier = Modifier.fillMaxHeight(.1F))
            Box(
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(15.dp, 15.dp, 15.dp, 15.dp))
                    .fillMaxHeight(main)
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
            Spacer(modifier = Modifier.fillMaxHeight(secondary))
            Row {
                var input by rememberSaveable { mutableStateOf("") }

                Box(modifier = Modifier
                    .clip(shape = RoundedCornerShape(15.dp, 15.dp, 15.dp, 15.dp))
                    .fillMaxWidth(.9F)
                    .fillMaxHeight(auxiliary)
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
                            sendMessage(input)

                            input = ""
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
                        .fillMaxHeight(auxiliary)
                        .background(Color(0xFF141414))
                ) {
                    IconButton(
                        onClick = {
                            sendMessage(input)
                            input = ""
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
                                .background(Color(Global.selectedTeam!!.theme[1])),
                        ) {
                            Text(
                                text = Global.selectedTeam!!.name,
                                modifier = Modifier
                                    .align(Alignment.Center),
                                color = Color(Global.selectedTeam!!.theme[3]),
                                fontFamily = TungstenFont(),
                                fontSize = 25.sp
                            )
                        }
                        Spacer(modifier = Modifier.fillMaxWidth(.05F))
                        Box(
                            modifier = Modifier
                                .fillMaxWidth(.3F)
                                .fillMaxHeight(.05F)
                                .background(Color(Global.selectedTeam!!.theme[2]))
                        )
                        Spacer(modifier = Modifier.fillMaxWidth(.09F))
                        Text(
                            text = "Coach: " + Global.selectedTeam!!.coach,
                            color = Color(Global.selectedTeam!!.theme[3]),
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
                                .background(Color(Global.selectedTeam!!.theme[2]))
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
                            .background(Color(Global.selectedTeam!!.theme[1]))
                            .border(BorderStroke(2.dp, Color(Global.selectedTeam!!.theme[0]))),
                        horizontalArrangement = Arrangement.Center,
                    ) {
                        Box(
                            modifier = Modifier.fillMaxHeight().fillMaxWidth(.22F).background(Color(Global.selectedTeam!!.theme[0])),
                        ) {
                            Image(
                                painter = painterResource(resource = agentMap[Global.selectedTeam!!.agents[0].lowercase()]?.get(0) ?: Res.drawable.VCT_Block),
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
                                text = Global.selectedTeam!!.members[0],
                                color = Color(Global.selectedTeam!!.theme[4]),
                                fontFamily = TungstenFont(),
                                fontSize = 45.sp
                            )
                            if(Global.selectedTeam!!.members[0] == Global.selectedTeam!!.igl) {
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
                                painter = painterResource(resource = agentMap[Global.selectedTeam!!.agents[0].lowercase()]?.get(1) ?: Res.drawable.VCT_Block),
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
                            .background(Color(Global.selectedTeam!!.theme[1]))
                            .border(BorderStroke(2.dp, Color(Global.selectedTeam!!.theme[0]))),
                        horizontalArrangement = Arrangement.Center,
                    ) {
                        Box(
                            modifier = Modifier.fillMaxHeight().fillMaxWidth(.22F).background(Color(Global.selectedTeam!!.theme[0])),
                        ) {
                            Image(
                                painter = painterResource(resource = agentMap[Global.selectedTeam!!.agents[1].lowercase()]?.get(0) ?: Res.drawable.VCT_Block),
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
                                text = Global.selectedTeam!!.members[1],
                                color = Color(Global.selectedTeam!!.theme[4]),
                                fontFamily = TungstenFont(),
                                fontSize = 45.sp
                            )
                            if(Global.selectedTeam!!.members[1] == Global.selectedTeam!!.igl) {
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
                                painter = painterResource(resource = agentMap[Global.selectedTeam!!.agents[1].lowercase()]?.get(1) ?: Res.drawable.VCT_Block),
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
                            .background(Color(Global.selectedTeam!!.theme[1]))
                            .border(BorderStroke(2.dp, Color(Global.selectedTeam!!.theme[0]))),
                        horizontalArrangement = Arrangement.Center,
                    ) {
                        Box(
                            modifier = Modifier.fillMaxHeight().fillMaxWidth(.22F).background(Color(Global.selectedTeam!!.theme[0])),
                        ) {
                            Image(
                                painter = painterResource(resource = agentMap[Global.selectedTeam!!.agents[2].lowercase()]?.get(0) ?: Res.drawable.VCT_Block),
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
                                text = Global.selectedTeam!!.members[2],
                                color = Color(Global.selectedTeam!!.theme[4]),
                                fontFamily = TungstenFont(),
                                fontSize = 45.sp
                            )
                            if(Global.selectedTeam!!.members[2] == Global.selectedTeam!!.igl) {
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
                                painter = painterResource(resource = agentMap[Global.selectedTeam!!.agents[2].lowercase()]?.get(1) ?: Res.drawable.VCT_Block),
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
                            .background(Color(Global.selectedTeam!!.theme[1]))
                            .border(BorderStroke(2.dp, Color(Global.selectedTeam!!.theme[0]))),
                        horizontalArrangement = Arrangement.Center,
                    ) {
                        Box(
                            modifier = Modifier.fillMaxHeight().fillMaxWidth(.22F).background(Color(Global.selectedTeam!!.theme[0])),
                        ) {
                            Image(
                                painter = painterResource(resource = agentMap[Global.selectedTeam!!.agents[3].lowercase()]?.get(0) ?: Res.drawable.VCT_Block),
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
                                text = Global.selectedTeam!!.members[3],
                                color = Color(Global.selectedTeam!!.theme[4]),
                                fontFamily = TungstenFont(),
                                fontSize = 45.sp
                            )
                            if(Global.selectedTeam!!.members[3] == Global.selectedTeam!!.igl) {
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
                                painter = painterResource(resource = agentMap[Global.selectedTeam!!.agents[3].lowercase()]?.get(1) ?: Res.drawable.VCT_Block),
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
                            .background(Color(Global.selectedTeam!!.theme[1]))
                            .border(BorderStroke(2.dp, Color(Global.selectedTeam!!.theme[0]))),
                        horizontalArrangement = Arrangement.Center,
                    ) {
                        Box(
                            modifier = Modifier.fillMaxHeight().fillMaxWidth(.22F).background(Color(Global.selectedTeam!!.theme[0])),
                        ) {
                            Image(
                                painter = painterResource(resource = agentMap[Global.selectedTeam!!.agents[4].lowercase()]?.get(0) ?: Res.drawable.VCT_Block),
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
                                text = Global.selectedTeam!!.members[4],
                                color = Color(Global.selectedTeam!!.theme[4]),
                                fontFamily = TungstenFont(),
                                fontSize = 45.sp
                            )
                            if(Global.selectedTeam!!.members[4] == Global.selectedTeam!!.igl) {
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
                                painter = painterResource(resource = agentMap[Global.selectedTeam!!.agents[4].lowercase()]?.get(1) ?: Res.drawable.VCT_Block),
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