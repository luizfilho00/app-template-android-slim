package com.facom.facomemfoco.data.storage

import android.content.Context
import android.content.SharedPreferences
import com.facom.facomemfoco.data.util.fromJson
import com.facom.facomemfoco.domain.boundary.resources.Cache
import com.facom.facomemfoco.domain.entity.Tag
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

object PreferencesCache : Cache {

    private const val PREFS_NAME = "APP_PREFERENCES"

    lateinit var sharedPreferences: SharedPreferences
        private set

    fun init(context: Context): Cache {
        sharedPreferences = context.getSharedPreferences(
                PREFS_NAME, Context.MODE_PRIVATE
        )
        return this
    }

    private val gson = Gson()

    override fun <T> get(key: String, type: Type): T {
        val stringValue = sharedPreferences.getString(key, null) ?: throw Cache.NotFoundException()
        return gson.fromJson(stringValue, type) ?: throw Cache.NotFoundException()
    }

    override fun getListOfTags(key: String): List<Tag> {
        val stringValue = sharedPreferences.getString(key, null) ?: throw Cache.NotFoundException()
        return gson.fromJson(stringValue)
    }

    override fun set(key: String, value: Any?) {
        if (value == null) {
            sharedPreferences.edit().remove(key).apply()
        } else {
            sharedPreferences.edit().putString(key, gson.toJson(value)).apply()
        }
    }

    override fun clear() {
        sharedPreferences.edit().clear().apply()
    }
}
