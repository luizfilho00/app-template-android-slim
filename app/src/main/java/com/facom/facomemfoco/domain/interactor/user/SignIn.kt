package com.facom.facomemfoco.domain.interactor.user

import com.facom.facomemfoco.domain.boundary.UserRepository
import com.facom.facomemfoco.domain.entity.User
import io.reactivex.Single

class SignIn(private val repository: UserRepository) {

    fun default(username: String, password: String, token: String?): Single<User> {
        return Single.just(FormFields().withUsername(username).withPassword(password))
                .doOnSuccess { formFields -> if (!formFields.isValid) throw formFields.exception }
                .flatMap { repository.signIn(username, password, token) }
                .doAfterSuccess { repository.cacheUser(it) }
    }
}