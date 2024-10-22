package org.xephyrous.com.JSInterop

object Tools {
    /**
     * Wrapper function for [JSUtils.teamDataToJSON] to handle errors
     * @param rawString The model's response string containing the team data as JSON
     * @param pureJSON Whether the string is pure JSON, or a full model response string
     * @return The inputted team data as a JS JSON object
     */
    fun teamDataToJSON(rawString: String, pureJSON: Boolean) : Result<JsAny> {
        val result =  JSUtils.teamDataToJSON(rawString, pureJSON)
            ?: return Result.failure(Exception(""))

        return Result.success(result)
    }

    /**
     * Wrapper function to parse the string returned from [Interop._extractJSONArray] into an array
     * @param key The key of the array in the JSON
     * @param caster The casting function to use in type conversion
     */
    inline fun <reified T> extractJSONArray(key: String, caster: (String) -> T, commas: Boolean = false) : Array<T> {
        val arr: Array<T>

        if (commas) {
            arr = JSUtils.extractJSONArray(key, true).split(",").map(caster).toTypedArray()
            for (i in arr.indices) { arr[i] = arr[i].toString().replace("~|COMMA|~", ",") as T }
        } else {
            arr = JSUtils.extractJSONArray(key, false).split(",").map(caster).toTypedArray()
        }

        return arr
    }

    /**
     * Wrapper function to parse the string returned from [Interop._extractJSONArray] into an array
     * @param key The key of the array in the JSON
     * @param caster The casting function to use in type conversion
     */
    inline fun <reified T> extractJSONObject(key: String, caster: (String) -> T) : T {
        return caster(JSUtils.extractJSONObject(key))
    }

    /**
     * Wrapper function exposing [JSUtils.cacheJSON]
     * @param json The JSON to cache
     */
    fun cacheJSON(json: JsAny) {
        JSUtils.cacheJSON(json)
    }

    /**
     * Wrapper function exposing [JSUtils.clearJSONCache]
     */
    fun clearJSONCache() {
        JSUtils.clearJSONCache()
    }
}