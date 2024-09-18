package org.xephyrous.com.JSInterop

@JsModule("./js/cookie-handler.js")
external object CookieHandler {
    fun addCookie(name: String, value: String) : JsAny?
    fun getCookie(name: String) : String
    fun removeCookie(name: String) : JsAny?
}