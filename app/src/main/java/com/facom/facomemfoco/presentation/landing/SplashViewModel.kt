package com.facom.facomemfoco.presentation.landing

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LiveData
import androidx.lifecycle.OnLifecycleEvent
import com.facom.facomemfoco.domain.interactor.user.GetPersistedUser
import com.facom.facomemfoco.presentation.structure.base.BaseViewModel
import com.facom.facomemfoco.presentation.util.extensions.defaultMutableLiveData

class SplashViewModel(
        private val getPersistedUser: GetPersistedUser
) : BaseViewModel() {

    val goToMain: LiveData<Boolean> get() = goToMainLiveData
    val goToLogin: LiveData<Boolean> get() = goToLoginLiveData

    private val goToMainLiveData = defaultMutableLiveData(false)
    private val goToLoginLiveData = defaultMutableLiveData(false)

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    private fun checkUser() {
        if (getPersistedUser.execute() == null) {
            goToLoginLiveData.postValue(true)
        } else {
            goToMainLiveData.postValue(true)
        }
    }
}