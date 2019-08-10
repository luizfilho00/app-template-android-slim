@file:Suppress("IMPLICIT_CAST_TO_ANY")

package com.facom.facomemfoco.presentation.structure.sl

import android.content.Context
import com.facom.facomemfoco.data.remote.repository.DefaultUserRepository
import com.facom.facomemfoco.data.storage.PreferencesCache
import com.facom.facomemfoco.domain.boundary.UserRepository
import com.facom.facomemfoco.domain.boundary.resources.Cache
import com.facom.facomemfoco.domain.boundary.resources.Logger
import com.facom.facomemfoco.domain.boundary.resources.SchedulerProvider
import com.facom.facomemfoco.domain.boundary.resources.StringsProvider
import com.facom.facomemfoco.domain.interactor.user.GetPersistedUser
import com.facom.facomemfoco.domain.interactor.user.RecoverPassword
import com.facom.facomemfoco.domain.interactor.user.SignIn
import com.facom.facomemfoco.domain.interactor.user.SignUp
import com.facom.facomemfoco.presentation.about.AboutViewModel
import com.facom.facomemfoco.presentation.landing.SplashViewModel
import com.facom.facomemfoco.presentation.login.LoginViewModel
import com.facom.facomemfoco.presentation.main.MainViewModel
import com.facom.facomemfoco.presentation.moreoptions.MoreOptionsViewModel
import com.facom.facomemfoco.presentation.newsall.NewsAllViewModel
import com.facom.facomemfoco.presentation.newsforyou.NewsForYouViewModel
import com.facom.facomemfoco.presentation.password.RecoverPasswordViewModel
import com.facom.facomemfoco.presentation.signup.SignUpViewModel
import com.facom.facomemfoco.presentation.util.ErrorHandler
import com.facom.facomemfoco.presentation.util.resources.AndroidLogger
import com.facom.facomemfoco.presentation.util.resources.AndroidStringProvider
import com.facom.facomemfoco.presentation.util.resources.DefaultSchedulerProvider
import com.facom.facomemfoco.presentation.util.resources.LoginAction

interface ServiceLocator {
    companion object {
        private var INSTANCE: ServiceLocator? = null
        fun getInstance() = INSTANCE
                ?: throw RuntimeException("Holy crap!! Service locator should have an instance already")

        fun getInstance(context: Context): ServiceLocator {
            return INSTANCE ?: DefaultServiceLocator(context).also {
                INSTANCE = it
            }
        }
    }

    val cache: Cache
    val logger: Logger
    val strings: StringsProvider
    val schedulerProvider: SchedulerProvider

    fun <T> get(type: Class<T>): T
}

class DefaultServiceLocator(private val context: Context) : ServiceLocator {

    override val cache: Cache
        get() = singletonCache
                ?: PreferencesCache.init(context).also { singletonCache = it }

    override val logger: Logger by lazy { AndroidLogger(context) }

    override val strings: StringsProvider by lazy { AndroidStringProvider(context) }

    override val schedulerProvider: SchedulerProvider by lazy { DefaultSchedulerProvider() }

    private var singletonCache: Cache? = null
    private val loginAction by lazy { LoginAction(context, cache) }

    @Suppress("UNCHECKED_CAST")
    override fun <T> get(type: Class<T>): T {
        return when (type) {
            /***
             * Utils
             ***/
            ErrorHandler::class.java -> ErrorHandler(strings, logger, loginAction)
            StringsProvider::class.java -> AndroidStringProvider(context)

            /***
             * Repositories
             ***/
            UserRepository::class.java -> DefaultUserRepository(cache)

            /***
             * Interactors
             ***/
            GetPersistedUser::class.java -> GetPersistedUser()
            SignIn::class.java -> SignIn(get(UserRepository::class.java))
            SignUp::class.java -> SignUp(get(UserRepository::class.java))
            RecoverPassword::class.java -> RecoverPassword(get(UserRepository::class.java))

            /***
             * ViewModels
             ***/
            SplashViewModel::class.java -> SplashViewModel(get(GetPersistedUser::class.java))
            LoginViewModel::class.java -> LoginViewModel(get(SignIn::class.java), schedulerProvider)
            MainViewModel::class.java -> MainViewModel(schedulerProvider)
            AboutViewModel::class.java -> AboutViewModel(schedulerProvider)
            MoreOptionsViewModel::class.java -> MoreOptionsViewModel(schedulerProvider)
            NewsForYouViewModel::class.java -> NewsForYouViewModel(schedulerProvider)
            NewsAllViewModel::class.java -> NewsAllViewModel(schedulerProvider)
            RecoverPasswordViewModel::class.java -> RecoverPasswordViewModel(
                    get(RecoverPassword::class.java),
                    schedulerProvider,
                    strings
            )
            SignUpViewModel::class.java -> SignUpViewModel(
                    get(SignUp::class.java),
                    schedulerProvider
            )
            else -> throw InstanceNotFoundException("${type::class.simpleName} was not found")
        } as T
    }
}