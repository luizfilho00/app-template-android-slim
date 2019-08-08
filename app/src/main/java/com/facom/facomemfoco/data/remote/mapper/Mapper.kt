package com.facom.facomemfoco.data.remote.mapper

abstract class Mapper<in I, out O> {
    abstract fun transform(t: I): O
    fun transform(items: List<I>?): List<O>? = items?.map(::transform)
}