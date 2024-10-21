package org.xephyrous.com

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.DpSize
import kotlinx.coroutines.*
import org.xephyrous.com.JSInterop.BedrockRuntime
import org.xephyrous.com.JSInterop.CookieHandler
import org.xephyrous.com.JSInterop.Firebase
import org.xephyrous.com.Utils.Global
import org.xephyrous.com.JSInterop.*
import org.xephyrous.com.UI.*
import org.xephyrous.com.Utils.*
import org.xephyrous.com.Utils.Global.initialized
import org.xephyrous.com.Utils.Global.initializing
import org.xephyrous.com.Utils.Global.sessionUUID

@OptIn(DelicateCoroutinesApi::class)
@Composable
fun App() {
    // Setup app connections and initialize resources
    if (!initialized && !initializing) {
        initializing = true

        GlobalScope.launch(Dispatchers.Default) {
            Firebase.initializeFirebase().onFailure {
                this.cancel("Could not initialize database connection!")
                TODO("Initialization failure UI alert")
            }

            CookieHandler.getCookie("vctSessionUUID").onFailure {
                this.cancel("Could not initialize session!")
                TODO("Enable cookies and reload application UI alert")
            }.onSuccess {
                if (it == "") {
                    Firebase.calculateSessionUUID().onSuccess { uuid -> sessionUUID = uuid }
                    Firebase.setSessionUUID(sessionUUID!!)

                    CookieHandler.addCookie("vctSessionUUID", sessionUUID!!)
                        .onFailure {
                            this.cancel("Could not initialize session!")
                            TODO("Enable cookies and reload application UI alert")
                        }

                    Firebase.createUser()
                } else {
                    CookieHandler.getCookie("vctSessionUUID")
                        .onSuccess { sessionUUID = it }
                        .onFailure {
                            this.cancel("Could not initialize session!")
                            TODO("Enable cookies and reload application UI alert")
                        }
                    Firebase.setSessionUUID(sessionUUID!!)
                }
            }

            // Get initial greeting
            BedrockRuntime.InvokeModel("Hello!").onFailure {
                this.cancel("Model failed to load response!")
                // TODO("Model failure UI alert")
            }.onSuccess { updateText(false, it) }

            initializing = false
            initialized = true
        }
    }

    // Main app structure
    MaterialTheme {
        BoxWithConstraints(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF1E1E1E)),
        ) {
            Global.viewSize = DpSize(maxWidth, maxHeight)

            UserChatField()
            TeamDisplay()
            Valiance()
            Settings()
        }
    }
}