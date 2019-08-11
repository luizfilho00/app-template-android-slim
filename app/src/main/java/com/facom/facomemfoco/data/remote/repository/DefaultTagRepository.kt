package com.facom.facomemfoco.data.remote.repository

import com.facom.facomemfoco.domain.boundary.TagRepository
import com.facom.facomemfoco.domain.boundary.resources.Cache
import com.facom.facomemfoco.domain.entity.Tag
import com.facom.facomemfoco.presentation.structure.sl.ServiceLocator

class DefaultTagRepository(private val cache: Cache) : TagRepository {
    override fun getTags(): List<Tag> {
        return getFromCache() ?: getFromServer()
    }

    override fun clearTagsFromCache() {
        cache.set(TagRepository.CURRENT_TAGS, emptyList<Tag>())
    }

    override fun cacheTags(tags: List<Tag>) {
        cache.set(TagRepository.CURRENT_TAGS, tags)
    }

    private fun getFromServer(): List<Tag> {
        return listOf(
                Tag("GENERAL"), //Não existe para Alunos!!!
                Tag("CC"),
                Tag("EC"),
                Tag("ES"),
                Tag("SI"),
                Tag("TADS"),
                Tag("TRC"),
                Tag("IC"),
                Tag("ESTÁGIO"),
                Tag("TCC"),
                Tag("EVENTO"),
                Tag("PALESTRA"),
                Tag("MONITORIA"),
                Tag("CONCURSO"),
                Tag("PROVA"),
                Tag("PÓS-GRADUAÇÃO")
        )
    }

    private fun getFromCache(): List<Tag>? {
        return try {
            ServiceLocator.getInstance().run {
                cache.getListOfTags(TagRepository.CURRENT_TAGS)
            }
        } catch (t: Throwable) {
            null
        }
    }
}