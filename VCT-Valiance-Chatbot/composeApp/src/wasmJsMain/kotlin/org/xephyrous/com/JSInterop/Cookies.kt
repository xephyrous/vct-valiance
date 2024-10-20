package org.xephyrous.com.JSInterop

import org.xephyrous.com.Utils.handleNull

object CookieHandler {
    fun addCookie(name: String, value: String) : Result<Unit> {
        JSCookieHandler.addCookie(name, value)
            ?: return Result.failure(Exception(""))

        return Result.success(Unit)
    }

    fun getCookie(name: String) : Result<String> {
        val cookie = JSCookieHandler.getCookie(name)
            ?: return Result.failure(Exception(""))

        return Result.success(cookie)
    }

    fun removeCookie(name: String) : Result<Unit> {
        JSCookieHandler.removeCookie(name)
            ?: return Result.failure(Exception(""))

        return Result.success(Unit)
    }
}