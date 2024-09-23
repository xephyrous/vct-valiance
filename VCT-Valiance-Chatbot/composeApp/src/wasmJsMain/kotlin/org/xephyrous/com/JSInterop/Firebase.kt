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
    fun addTeam(teamObj: String) : Promise<JsAny>
}

class TeamData(teamMessage: String) {
    lateinit var name: String
    lateinit var igl: String
    lateinit var coach: String
    lateinit var members: Array<String>
    lateinit var roles: Array<String>
    lateinit var agents: Array<String>
    lateinit var theme: Array<String>
}