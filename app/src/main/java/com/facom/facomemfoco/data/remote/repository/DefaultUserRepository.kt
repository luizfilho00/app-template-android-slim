package com.facom.facomemfoco.data.remote.repository

import com.facom.facomemfoco.data.remote.client.ApiClient
import com.facom.facomemfoco.data.remote.entity.ApiUser
import com.facom.facomemfoco.data.remote.entity.Fields
import com.facom.facomemfoco.domain.boundary.UserRepository
import com.facom.facomemfoco.domain.boundary.resources.Cache
import com.facom.facomemfoco.domain.entity.User
import com.facom.facomemfoco.domain.interactor.user.SignUp
import io.reactivex.Completable
import io.reactivex.Single

class DefaultUserRepository(private val cache: Cache) : UserRepository {
    override fun getCurrentFromRemote(): Single<User> {
        TODO("Not implemented")
    }

    override fun signIn(username: String, password: String, token: String?): Single<User> {
        return ApiClient.signIn(username, password, token).map(ApiUser.ApiUserToUserMapper::transform)
    }

    override fun signInWithFacebook(): Single<User> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun signUp(user: SignUp.Fields): Single<User> {
        return ApiClient.signUp(
                Fields.with("name", user.name)
                        .and("email", user.email)
                        .and("phone_number", user.phoneNumber)
                        .and("cpf", user.cpf)
                        .and("password", user.password)
                        .and("avatar", user.avatar)
        )
                .map(({ ApiUser.ApiUserToUserMapper.transform(it) }))
                .doOnSuccess(({ this.cacheUser(it) }))
    }

    override fun sendPasswordRecovery(email: String): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun cacheUser(user: User) {
        cache.set(UserRepository.CURRENT_USER, user)
    }
}