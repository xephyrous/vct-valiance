package org.xephyrous.com.JSInterop

import kotlin.js.Promise

enum class Role(val strValue: String) {
    USER("user"),
    SYSTEM("system")
}

@JsModule("./js/firebase-api.js")
external object Firebase {
    fun initializeFirebase() : Promise<JsAny>
    fun createUser() : Promise<JsAny>
    fun calculateSessionUUID() : Promise<JsAny>
    fun setSessionUUID(uuid: String)
    fun debug(str: String)
    fun addMessage(message: String, role: String) : Promise<JsAny>
}