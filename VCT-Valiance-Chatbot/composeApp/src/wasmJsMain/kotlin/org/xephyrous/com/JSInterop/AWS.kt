package org.xephyrous.com.JSInterop

import kotlinx.coroutines.await
import org.xephyrous.com.Utils.ErrorType
import org.xephyrous.com.Utils.awaitHandled

object BedrockRuntime {
    suspend fun InvokeModel(prompt: String) : Result<String> {
        val result = JSBedrockRuntime.InvokeModel(prompt).awaitHandled<JsString>(ErrorType.MODEL_RESPONSE)
            ?: return Result.failure(Exception(""))

        return Result.success(result.toString())
    }
}