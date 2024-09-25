package org.xephyrous.com.JSInterop

class TeamData(teamMessage: String) {
    lateinit var name: String
    lateinit var igl: String
    lateinit var coach: String
    lateinit var members: Array<String>
    lateinit var roles: Array<String>
    lateinit var agents: Array<String>
    lateinit var theme: Array<Long>
}

@JsModule("./js/js-utils.js")
external class JSUtils {
    
}