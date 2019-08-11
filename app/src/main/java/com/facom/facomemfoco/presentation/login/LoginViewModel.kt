package com.facom.facomemfoco.presentation.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.facom.facomemfoco.domain.boundary.resources.SchedulerProvider
import com.facom.facomemfoco.domain.extensions.defaultSched
import com.facom.facomemfoco.domain.interactor.user.InvalidFieldsException
import com.facom.facomemfoco.domain.interactor.user.LoginForm
import com.facom.facomemfoco.domain.interactor.user.SignIn
import com.facom.facomemfoco.presentation.structure.base.BaseViewModel
import com.facom.facomemfoco.presentation.util.extensions.defaultPlaceholders
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy

class LoginViewModel(
        private val signIn: SignIn,
        private val schedulerProvider: SchedulerProvider
) : BaseViewModel() {

    val showUsernameFieldError: LiveData<Boolean> get() = showUsernameFieldErrorLiveData
    val showPasswordFieldError: LiveData<Boolean> get() = showPasswordFieldErrorLiveData
    val goToMain: LiveData<Boolean> get() = goToMainLiveData

    private val showUsernameFieldErrorLiveData: MutableLiveData<Boolean> = MutableLiveData()
    private val showPasswordFieldErrorLiveData: MutableLiveData<Boolean> = MutableLiveData()
    private val goToMainLiveData: MutableLiveData<Boolean> = MutableLiveData()

    private var form = LoginForm()
    private var signInDisposable: Disposable? = null


    fun onUsernameChanged(username: String) {
        form.username = username
    }

    fun onPasswordChanged(password: String) {
        form.password = password
    }

    fun onSubmitClicked() {
        form.useForm(this::submit)?.let { showFieldErrors(it) }
    }

    private fun submit(username: String, password: String) {
        signInDisposable?.dispose()
        signInDisposable = signIn.default(username, password, null)
                .defaultPlaceholders(this::setPlaceholder)
                .defaultSched(schedulerProvider)
                .subscribeBy(::onFailure) {
                    showUsernameFieldErrorLiveData.value = false
                    showPasswordFieldErrorLiveData.value = false
                    goToMainLiveData.value = true
                }
    }

    private fun onFailure(throwable: Throwable) {
        if (throwable is InvalidFieldsException) {
            showFieldErrors(throwable)
        } else {
            setDialog(throwable, this::onSubmitClicked)
        }
    }

    private fun showFieldErrors(e: InvalidFieldsException) {
        for (field in e.getFields()) {
            showFieldError(field)
        }
    }

    private fun showFieldError(field: Int) {
        when (field) {
            InvalidFieldsException.USERNAME -> showUsernameFieldErrorLiveData.value = true
            InvalidFieldsException.PASSWORD -> showPasswordFieldErrorLiveData.value = true
        }
    }
}