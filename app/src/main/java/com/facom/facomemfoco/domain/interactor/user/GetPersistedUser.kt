package com.facom.facomemfoco.domain.interactor.user

import com.facom.facomemfoco.domain.boundary.UserRepository
import com.facom.facomemfoco.domain.entity.User
import com.facom.facomemfoco.presentation.structure.sl.ServiceLocator

class GetPersistedUser {
    fun execute(): User? {
        return try {
            ServiceLocator.getInstance().run {
                cache.get(UserRepository.CURRENT_USER, User::class.java) as User
            }
        } catch (t: Throwable) {
            null
        }
    }
}