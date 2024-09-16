package org.xephyrous.com

import androidx.compose.animation.core.*
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

class ChatBox (
    private val user: Boolean = false,
    private val text: String
){
    private var loaded: Boolean = false

    @Composable
    fun createBox(){
        var textToAnimate by remember { mutableStateOf("") }

        val index = remember {
            Animatable(initialValue = 0, typeConverter = Int.VectorConverter)
        }

        val spec: AnimationSpec<Int> = tween(durationMillis = text.length * 100, easing = LinearEasing)

        val isVisible = true

        LaunchedEffect(isVisible) {
            if (!loaded) {
                textToAnimate = text
                index.animateTo(text.length, spec)
                loaded = true
            } else {
                index.snapTo(0)
            }
        }


        Box(
            modifier = Modifier
                .widthIn(0.dp, 800.dp)
                .background(Color(if (user) 0x55fd4556 else 0x55000000))
                .border(BorderStroke(1.dp, Color.Black))
        ) {
            Text(
                text = textToAnimate.substring(0, index.value),
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(5.dp),
                color = Color.LightGray,
                fontFamily = TungstenFont()

            )
        }
        Spacer(Modifier.size(5.dp))
    }
}