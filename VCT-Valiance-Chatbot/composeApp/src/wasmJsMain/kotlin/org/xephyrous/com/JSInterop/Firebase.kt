package org.xephyrous.com.JSInterop

@JsModule("./js/firebase-api.js")
external object Firebase {
    fun createUser() : String
}