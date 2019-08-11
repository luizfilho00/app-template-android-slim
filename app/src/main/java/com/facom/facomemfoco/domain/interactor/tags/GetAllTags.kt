package com.facom.facomemfoco.domain.interactor.tags

import com.facom.facomemfoco.domain.entity.Tag
import io.reactivex.Single

class GetAllTags {

    fun execute(): Single<List<Tag>> {
        return Single.just(
                listOf(
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
        )
    }
}