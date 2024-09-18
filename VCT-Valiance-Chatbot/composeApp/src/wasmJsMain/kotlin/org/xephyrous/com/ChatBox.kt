package org.xephyrous.com

import androidx.compose.animation.AnimatedVisibility
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
import org.xephyrous.com.UI.TungstenFont

/*
   ok this is kinda annoying to explain

   there is an internal isVisible modifier rn that handles if the boxes are visible.
   there is ALSO a loaded variable that determines if the text will animate, false means animate, true means no animate (ik weird)
   user boolean determines sides and colors (user and bot).
   text should be self-explanatory
 */
class ChatBox (
    private val user: Boolean = false,
    private val text: String,
    private var loaded: Boolean = false
){

    @Composable
    fun createBox(){
        var textToAnimate by remember { mutableStateOf("") }

        val index = remember {
            Animatable(initialValue = 0, typeConverter = Int.VectorConverter)
        }

        val isVisible = true

        LaunchedEffect(isVisible) {
            if (!loaded) {
                textToAnimate = text
                index.animateTo(text.length, tween(durationMillis = text.length * 50, easing = LinearEasing))
                loaded = true
            } else {
                textToAnimate = text
                index.animateTo(text.length, tween(durationMillis = 0, easing = LinearEasing))
            }
        }


        Row(
            modifier = Modifier.fillMaxWidth().width(IntrinsicSize.Min),
            horizontalArrangement = if (user) Arrangement.End else Arrangement.Start,
        ){
            AnimatedVisibility(
                visible = isVisible
            ){
                Box(
                    modifier = Modifier
                        .widthIn(0.dp, 500.dp)
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
            }
        }
        Spacer(Modifier.size(if (user) 5.dp else 10.dp))
    }
}