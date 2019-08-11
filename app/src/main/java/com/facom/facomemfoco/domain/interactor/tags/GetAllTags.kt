package com.facom.facomemfoco.domain.interactor.tags

import com.facom.facomemfoco.domain.entity.Tag

class GetAllTags {

    fun execute(): List<Tag> {
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
}