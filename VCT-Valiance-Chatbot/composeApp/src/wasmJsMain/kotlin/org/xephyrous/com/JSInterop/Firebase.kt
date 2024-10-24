package org.xephyrous.com.JSInterop

import org.xephyrous.com.Utils.ErrorType
import org.xephyrous.com.Utils.TeamData
import org.xephyrous.com.Utils.awaitHandled
import org.xephyrous.com.Utils.awaitHandledUnit
import vctvaliancechatbot.composeapp.generated.resources.Res

enum class Role(val strValue: String) {
    USER("user"),
    SYSTEM("system")
}

object Firebase {
    /**
     * Wrapper for [JSFirebase.initializeFirebase] to handle errors
     * @return The success state of the initialization
     */
    suspend fun initializeFirebase() : Result<Unit> {
        return JSFirebase.initializeFirebase().awaitHandledUnit(ErrorType.DATABASE_INIT)
            .onSuccess { Result.success(Unit) }
            .onFailure { Result.failure<Unit>(Exception("")) }
    }

    /**
     * Wrapper for [JSFirebase.createUser] to handle errors
     * @return The success state of the operation
     */
    suspend fun createUser() : Result<Unit> {
        return JSFirebase.createUser().awaitHandledUnit(ErrorType.DATABASE_INIT)
            .onSuccess { Result.success(Unit) }
            .onFailure { Result.failure<Unit>(Exception("")) }
    }

    /**
     * Wrapper for [JSFirebase.calculateSessionUUID] to handle errors
     * @return The success state of the operation
     */
    suspend fun calculateSessionUUID() : Result<String> {
        return JSFirebase.calculateSessionUUID().awaitHandled<JsString>(ErrorType.HASH)
            ?.let { Result.success(it.toString()) }
            ?: Result.failure(Exception(""))
    }

    /**
     * Wrapper function exposing [JSFirebase.setSessionUUID]
     * @param uuid The hashed session UUID
     */
    fun setSessionUUID(uuid: String) {
        JSFirebase.setSessionUUID(uuid)
    }

    /**
     * Wrapper function for [JSFirebase.addMessage] to handle errors
     * @param message The message to add to the database list
     * @param role The role of message sender
     * @return The success state of the operation
     */
    suspend fun addMessage(message: String, role: String) : Result<Unit> {
        return JSFirebase.addMessage(message, role).awaitHandledUnit(ErrorType.DATABASE_INIT)
            .onSuccess { Result.success(Unit) }
            .onFailure { Result.failure<Unit>(Exception("")) }
    }

    /**
     *  Wrapper function fo [JSFirebase.addTeam] to handle errors
     *  @param teamObj The team object as JSON to add to the database list
     *  @return The success state of the operation
     */
    suspend fun addTeam(teamObj: String) : Result<Unit> {
        return JSFirebase.addTeam(teamObj).awaitHandledUnit(ErrorType.DATABASE_INIT)
            .onSuccess { Result.success(Unit) }
            .onFailure { Result.failure<Unit>(Exception("")) }
    }

    /**
     * Wrapper for [JSFirebase.getTeamNames] to convert to an [Array] and to handle errors
     * @return An [Array] of all stored team names
     */
    suspend fun getTeamNames() : Result<Array<String>> {
        val data = JSFirebase.getTeamNames().awaitHandled<JsAny>(ErrorType.DATABASE_GET)
            ?: return Result.failure(Exception(""))

        JSUtils.cacheJSON(data)
        val names = Tools.extractJSONArray("names", String::toString, false)
        JSUtils.clearJSONCache()

        return Result.success(names)
    }

    /**
     * Wrapper for [JSFirebase.getTeamUUIDs] to convert to an [Array] and to handle errors
     * @return An [Array] of all stored team UUIDs
     */
    suspend fun getTeamUUIDs() : Result<Array<String>> {
        val data = JSFirebase.getTeamUUIDs().awaitHandled<JsAny>(ErrorType.DATABASE_GET)
            ?: return Result.failure(Exception(""))

        JSUtils.cacheJSON(data)
        val uuids = Tools.extractJSONArray("uuids", String::toString, false)
        JSUtils.clearJSONCache()

        return Result.success(uuids)
    }

    /**
     * Wrapper function for [JSFirebase.getTeamByUUID] to convert to a [TeamData] object,
     * and to handle errors
     * @param index The UUID of the team object to retrieve
     * @return A TeamData object at the given position in the database
     */
    suspend fun getTeamByUUID(uuid: String) : Result<TeamData> {
        return JSFirebase.getTeamByUUID(uuid).awaitHandled<JsString>(ErrorType.DATABASE_GET)
            ?.let {
                JSFirebase.debug("Returned")
                val data = TeamData()
                data.loadJSON(it.toString(), true)
                JSFirebase.debug("Finished?")
                Result.success(data)
            }
            ?: Result.failure(Exception(""))
    }

    /**
     * Wrapper function for [JSFirebase.getMessages] to convert to a [Pair] object,
     * and to handle errors
     */
    suspend fun getMessages() : Result<Pair<Array<String>, Array<String>>> {
        val data = JSFirebase.getMessages().awaitHandled<JsAny>(ErrorType.DATABASE_GET)
            ?: return Result.failure(Exception(""))

        JSUtils.cacheJSON(data)
        val sysMsg = Tools.extractJSONArray("systemMessages", String::toString, true)
        val usrMsg = Tools.extractJSONArray("userMessages", String::toString, true)
        JSUtils.clearJSONCache()

        return Result.success(Pair(sysMsg, usrMsg))
    }

    suspend fun updateAccessTime() : Result<Unit> {
        return JSFirebase.updateAccessTime().awaitHandledUnit(ErrorType.DATABASE_INIT)
            .onSuccess { Result.success(Unit) }
            .onFailure { Result.failure<Unit>(Exception("")) }
    }
}