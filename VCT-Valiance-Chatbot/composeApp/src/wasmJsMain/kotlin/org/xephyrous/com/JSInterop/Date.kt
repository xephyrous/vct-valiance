package org.xephyrous.com.JSInterop

/**
 * Interop class for the (JavaScript Date Class)[https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Date]
 */
@JsName("Date")
external class JsDate {
    fun getUTCFullYear(): Int
    fun getUTCMonth(): Int
    fun getUTCDate(): Int
    fun getUTCHours(): Int
    fun getUTCMinutes(): Int
    fun getUTCSeconds(): Int
}

/**
 * Gets the current time in UTC, formatted using ISO-8601
 * @return The current time (UTC) in the ISO-8601 format as a [String]
 */
fun getUTCTimeISO8601(): String {
    val date = JsDate()

    val year = date.getUTCFullYear()
    val month = (date.getUTCMonth() + 1).toString().padStart(2, '0') // Months are 0-indexed
    val day = date.getUTCDate().toString().padStart(2, '0')
    val hours = date.getUTCHours().toString().padStart(2, '0')
    val minutes = date.getUTCMinutes().toString().padStart(2, '0')
    val seconds = date.getUTCSeconds().toString().padStart(2, '0')

    return "$year$month${day}T$hours$minutes${seconds}Z"
}