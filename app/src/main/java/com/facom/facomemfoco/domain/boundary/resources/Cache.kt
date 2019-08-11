package com.facom.facomemfoco.domain.boundary.resources

import com.facom.facomemfoco.domain.entity.Tag
import java.lang.reflect.Type

interface Cache {
    @Throws(Cache.NotFoundException::class)
    fun <T> get(key: String, type: Type): T

    @Throws(Cache.NotFoundException::class)
    fun getListOfTags(key: String): List<Tag>

    fun set(key: String, value: Any?)

    fun clear()

    class NotFoundException : Exception()
}