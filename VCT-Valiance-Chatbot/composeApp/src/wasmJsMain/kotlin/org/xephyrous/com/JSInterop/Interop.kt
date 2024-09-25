package org.xephyrous.com.JSInterop

import kotlin.js.Promise

@JsModule("./js/js-utils.js")
external object JSUtils {
    fun teamDataToJSON(rawString: String, pureJSON: Boolean) : JsAny?
    fun extractJSONObject(key: String) : String
    fun extractJSONArray(key: String) : String
    fun cacheJSON(json: JsAny)
    fun clearJSONCache()
}

@JsModule("./js/firebase-api.js")
external object JSFirebase {
    fun initializeFirebase() : Promise<JsAny>
    fun createUser() : Promise<JsAny>
    fun calculateSessionUUID() : Promise<JsString>
    fun setSessionUUID(uuid: String)
    fun addMessage(message: String, role: String) : Promise<JsAny>
    fun addTeam(teamObj: String) : Promise<JsAny>
    fun getTeamNames() : Promise<JsArray<JsString>>
    fun getTeamIndexed(index: Int) : Promise<JsString>
    fun updateAccessTime() : Promise<JsAny>

    fun debug(str: String) // TODO : Remove for production
}

@JsModule("./js/aws-api.js")
external object JSBedrockRuntime {
    fun InvokeModel(prompt: String) : Promise<JsString>
}

@JsModule("./js/cookie-handler.js")
external object JSCookieHandler {
    fun addCookie(name: String, value: String) : JsAny?
    fun getCookie(name: String) : String?
    fun removeCookie(name: String) : JsAny?
}

@JsName("Date")
external class JsDate {
    fun getUTCFullYear(): Int
    fun getUTCMonth(): Int
    fun getUTCDate(): Int
    fun getUTCHours(): Int
    fun getUTCMinutes(): Int
    fun getUTCSeconds(): Int
}