package org.xephyrous.com.JSInterop

@JsModule("./js/aws-api.js")
external object BedrockRuntime {
    fun InvokeModel(prompt: String) : String
}