package com.facom.facomemfoco.domain.entity

data class Tag(
        var name: String? = null,
        var id: Int? = null,
        var position: Int? = null,
        var selected: Boolean = false
)