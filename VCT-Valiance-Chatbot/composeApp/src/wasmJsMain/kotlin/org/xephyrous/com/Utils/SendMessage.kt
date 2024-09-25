package org.xephyrous.com.Utils

import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.xephyrous.com.ChatBox
import org.xephyrous.com.JSInterop.BedrockRuntime
import org.xephyrous.com.Utils.ErrorType.MODEL_RESPONSE

fun updateText(
    user: Boolean,
    text: String
) {
    val temp: ArrayList<ChatBox> = arrayListOf()
    temp.addAll(Global.loadedMessages)
    temp.add(ChatBox(user, text))
    Global.loadedMessages = temp
}

@OptIn(DelicateCoroutinesApi::class)
fun sendMessage(
    input: String
) {
    if (input.isNotEmpty() || Global.sendingMessage) {
        Global.sendingMessage = true

        // Upload the message to the screen
        updateText(true, input)

        // Send query and wait for response
        GlobalScope.launch(Dispatchers.Default) {
            updateText(false, BedrockRuntime.InvokeModel(input).awaitHandled(MODEL_RESPONSE).toString())
            Global.sendingMessage = false
        }
    }
}