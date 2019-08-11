package com.facom.facomemfoco.domain.boundary

import com.facom.facomemfoco.domain.entity.Tag

interface TagRepository {

    fun getTags(): List<Tag>
    fun clearTagsFromCache()
    fun cacheTags(tags: List<Tag>)

    companion object {
        const val CURRENT_TAGS = "CURRENT_TAGS"
    }
}