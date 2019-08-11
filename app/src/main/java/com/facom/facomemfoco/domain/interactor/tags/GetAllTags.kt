package com.facom.facomemfoco.domain.interactor.tags

import com.facom.facomemfoco.domain.boundary.TagRepository
import com.facom.facomemfoco.domain.entity.Tag
import io.reactivex.Single

class GetAllTags(
        private val tagRepository: TagRepository
) {

    fun execute(): Single<List<Tag>> {
        return Single.just(tagRepository.getTags())
    }
}