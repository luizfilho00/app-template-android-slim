package com.facom.facomemfoco.domain.interactor.tags

import com.facom.facomemfoco.domain.boundary.TagRepository
import com.facom.facomemfoco.domain.entity.Tag
import io.reactivex.Completable
import io.reactivex.Single

class PersistTags(
        private val tagRepository: TagRepository
) {

    fun execute(tags: List<Tag>): Completable {
        return Single.just(tagRepository.cacheTags(tags)).ignoreElement()
    }
}