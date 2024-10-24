package org.xephyrous.com.Utils

import kotlinx.coroutines.*
import org.xephyrous.com.ChatBox
import org.xephyrous.com.JSInterop.BedrockRuntime
import org.xephyrous.com.JSInterop.Firebase
import org.xephyrous.com.JSInterop.JSFirebase
import org.xephyrous.com.JSInterop.Tools
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

fun updateText(
    message: ChatBox
) {
    val temp: ArrayList<ChatBox> = arrayListOf()
    temp.addAll(Global.loadedMessages)
    temp.add(message)
    Global.loadedMessages = temp
}

fun updateText() {
    val temp: ArrayList<ChatBox> = arrayListOf()
    temp.addAll(Global.loadedMessages)
    Global.loadedMessages = temp
}

fun debugText(
    text: String
) {
    updateText(true, "DEBUG: $text")
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
            // Add user message
            Firebase.addMessage(input, "user")

            delay(input.length*25L)

            val mlResponse = ChatBox(false, "", false, true)

            updateText(mlResponse)

            // Validate prompt prior to sending
            Validator.validatePrompt(input).onSuccess { validation ->
                if (validation.first) { // Good response

                    // Get RAG data for context
                    BedrockRuntime.InvokeRAG(input).onFailure {
                        this.cancel("Model failed to load response!")
                        // TODO("Model failure UI alert")
                    }.onSuccess { response ->
                        var text: String = response

                        if (response.contains("~||TEAMDATA||~")) {
                            text = response.substring(0, response.indexOf("~||TEAMDATA||~") - 2)

                            // Load JSON to UI
                            val newTeam = TeamData()
                            newTeam.loadJSON(response, false)

                            Firebase.addTeam(response).onFailure { /* Uhhhhhhhhhhhhh nah */ }

                            Global.selectedTeam = newTeam
                            Global.displayingTeam = true
                        }

                        // Display to screen
                        mlResponse.finalizeLoad(text)

                        // Add system message
                        Firebase.addMessage(text, "system")
                    }
                    Global.sendingMessage = false

                    // Get full response with data

                    return@launch
                }

                // Response flagged
                mlResponse.finalizeLoad(validation.second)
                Firebase.addMessage(validation.second, "system")
            }.onFailure {
                // TODO("Failed to validate prompt UI alert)
            }
            Global.sendingMessage = false
        }
    }
}