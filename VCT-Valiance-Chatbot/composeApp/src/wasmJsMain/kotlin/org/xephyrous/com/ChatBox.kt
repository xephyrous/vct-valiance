package org.xephyrous.com

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.*
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import org.xephyrous.com.UI.TungstenFont
import org.xephyrous.com.Utils.Global

/*
   ok this is kinda annoying to explain

   there is an internal isVisible modifier rn that handles if the boxes are visible.
   there is ALSO a loaded variable that determines if the text will animate, false means animate, true means no animate (ik weird)
   user boolean determines sides and colors (user and bot).
   text should be self-explanatory
 */
class ChatBox (
    private val user: Boolean = false,
    private var text: String,
    private var loaded: Boolean = false,
    waitForLoad: Boolean = false // ANY TIME WAITFORLOAD IS CALLED YOU MUST FOLLOW WITH A MATCHING FINALIZELOAD FUNCTION
){
    var loading by mutableStateOf(waitForLoad)

    private var isVisible by mutableStateOf(!waitForLoad)
    @Composable
    fun createBox(){
        var textToAnimate by remember { mutableStateOf("") }

        val index = remember {
            Animatable(initialValue = 0, typeConverter = Int.VectorConverter)
        }

        LaunchedEffect(isVisible) {
            if (!loaded && isVisible) {
                textToAnimate = text
                index.animateTo(text.length, tween(durationMillis = text.length * 25, easing = LinearEasing))
                loaded = true
            } else {
                textToAnimate = text
                index.animateTo(text.length, tween(durationMillis = 0, easing = LinearEasing))
            }
        }

        var waitingIndex by remember { mutableStateOf(0) }

        var waitingText by remember { mutableStateOf("") }

        LaunchedEffect(
            key1 = loading,
        ) {
            if (loading && !isVisible) {
                while (waitingText.length < "Generating Responses...".length) {
                    waitingText = "Generating Response"
                        .substring(
                            startIndex = 0,
                            endIndex = ++waitingIndex,
                        )
                    delay(100)
                    while (loading) { // yknow this is definitely a bad way to do this but im really tired so idc...
                        waitingText = "Generating Response"
                        delay(100)
                        waitingText = "Generating Response."
                        delay(100)
                        waitingText = "Generating Response.."
                        delay(100)
                        waitingText = "Generating Response..."
                        delay(250)
                    }
                }

            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = if (user) Arrangement.End else Arrangement.Start,
        ){

            Row (
                modifier = Modifier
                    .widthIn(0.dp, 500.dp)
                    .background(Color(if (user) 0x55fd4556 else 0x55000000))
                    .border(BorderStroke(1.dp, Color.Black))
            ) {
                Text(
                    text = if (loading) waitingText else textToAnimate.substring(0, index.value),
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(5.dp),
                    color = Color.LightGray,
                    fontFamily = TungstenFont()
                )
            }
        }

        Spacer(Modifier.size(if (user) 5.dp else 10.dp))
    }

    fun finalizeLoad(string: String) {
        text = string
        loading = false
        isVisible = true
    }
}