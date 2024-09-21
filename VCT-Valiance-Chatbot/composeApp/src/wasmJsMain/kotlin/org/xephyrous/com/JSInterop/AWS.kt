package org.xephyrous.com.JSInterop

import kotlin.js.Promise

@JsModule("./js/aws-api.js")
external object BedrockRuntime {
    fun InvokeModel(prompt: String) : Promise<JsString>
}