package com.facom.facomemfoco.domain.boundary.resources

import java.lang.reflect.Type

interface Cache {
    @Throws(Cache.NotFoundException::class)
    fun <T> get(key: String, type: Type): T

    fun set(key: String, value: Any?)

    fun clear()

    class NotFoundException : Exception()
}
