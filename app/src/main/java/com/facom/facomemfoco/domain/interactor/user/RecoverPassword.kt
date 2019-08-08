package com.facom.facomemfoco.domain.interactor.user

import com.facom.facomemfoco.domain.boundary.UserRepository
import io.reactivex.Completable

class RecoverPassword constructor(
        private val repository: UserRepository
) {
    fun execute(email: String): Completable {
        val formFields = FormFields().withEmail(email)
        if (!formFields.isValid) return Completable.error(formFields.exception)
        return repository.sendPasswordRecovery(email)
    }
}