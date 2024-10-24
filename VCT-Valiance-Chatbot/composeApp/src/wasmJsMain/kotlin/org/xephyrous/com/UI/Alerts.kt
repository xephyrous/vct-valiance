package org.xephyrous.com.UI

import androidx.compose.animation.core.EaseInOut
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.*
import org.xephyrous.com.Utils.Global


object Alerts {
    private var text: String = ""
    private var displayed by mutableStateOf(false)

    @Composable
    fun createAlert() {
        val open by animateFloatAsState(
            targetValue = if (displayed) .95f else 1F,
            animationSpec = tween(durationMillis = 300, easing = EaseInOut)
        )
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Spacer(
                modifier = Modifier.fillMaxHeight(open)
            )
            Column(
                modifier = Modifier
                    .fillMaxHeight().fillMaxWidth(.5F)
                    .background(Color(0xFF141414))
                    .clip(shape = RoundedCornerShape(15.dp, 15.dp, 0.dp, 0.dp))
                    .align(Alignment.CenterHorizontally)
            ) {
                Box(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text(
                        text = text,
                        modifier = Modifier.align(Alignment.Center),
                        color = Color(0xFFFD4556),
                        fontFamily = TungstenFont(),
                        fontSize = 30.sp
                    )
                }
            }
        }
    }

    /*
    * Launches alert displayedText which does not go away
    */
    @OptIn(DelicateCoroutinesApi::class)
    fun displayUnmovingAlert(
        displayedText: String
    ) {
        GlobalScope.launch(Dispatchers.Default) {
            waitForCondition(50000, 100)

            // Update the UI
            this@Alerts.text = displayedText
            this@Alerts.displayed = true
        }
    }

    /*
    * Launches alert displayedText which goes away after displayTime ms
    */
    @OptIn(DelicateCoroutinesApi::class)
    fun displayAlert(
        displayedText: String,
        displayTime: Long = 3000
    ) {
        GlobalScope.launch(Dispatchers.Default) {
            waitForCondition(50000, 100)

            // Update the UI
            this@Alerts.text = displayedText
            this@Alerts.displayed = true

            // Schedule hiding the alert after a delay
            withContext(Dispatchers.Default) {
                delay(displayTime)
                GlobalScope.launch(Dispatchers.Default) {
                    this@Alerts.displayed = false
                }
            }
        }
    }

    private suspend fun waitForCondition(maxDelay: Long, checkPeriod: Long): Boolean {
        if (maxDelay < 0) return false
        if (!this@Alerts.displayed) return true
        delay(checkPeriod)
        return waitForCondition(maxDelay - checkPeriod, checkPeriod)
    }
}