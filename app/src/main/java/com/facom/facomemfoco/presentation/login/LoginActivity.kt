package com.facom.facomemfoco.presentation.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.facom.facomemfoco.R
import com.facom.facomemfoco.databinding.ActivityLoginBinding
import com.facom.facomemfoco.presentation.structure.base.BaseActivity
import com.facom.facomemfoco.presentation.structure.base.BaseViewModel
import com.facom.facomemfoco.presentation.structure.navigation.Navigator
import com.facom.facomemfoco.presentation.structure.sl.ServiceLocator
import com.facom.facomemfoco.presentation.util.extensions.*

class LoginActivity : BaseActivity() {

    override val sl: ServiceLocator get() = ServiceLocator.getInstance(this.applicationContext)
    override val baseViewModel: BaseViewModel get() = viewModel

    private lateinit var viewModel: LoginViewModel
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        viewModel = sl.get(LoginViewModel::class.java)
        lifecycle.addObserver(viewModel)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        setupToolbar(binding.includedToolbar.toolbar, true, getString(R.string.activity_login_title))
        setupUi()
        super.onCreate(savedInstanceState)
    }

    override fun subscribeUi() {
        super.subscribeUi()
        with(viewModel){
            showUsernameFieldError.observe(this@LoginActivity, ::onNextUsernameError)
            showPasswordFieldError.observe(this@LoginActivity, ::onNextPasswordError)
            goToMain.observe(this@LoginActivity, ::onNextGoToMain)
        }
    }

    private fun setupUi() {
        with(binding){
            usernameInput.observeChanges(viewModel::onUsernameChanged)
            passwordInput.observeChanges(viewModel::onPasswordChanged)
            submitButton.setOnClickListener(viewModel::onSubmitClicked)
        }
    }

    private fun onNextGoToMain(shouldGo: Boolean?) {
        shouldGo?.let { Navigator.goToMain(this, true) }
    }

    private fun onNextUsernameError(shouldShowError: Boolean?) {
        shouldShowError?.let {
            shortToast(R.string.activity_login_alert_username)
        }
    }

    private fun onNextPasswordError(shouldShowError: Boolean?) {
        shouldShowError?.let {
            shortToast(R.string.activity_login_alert_password)
        }
    }

    companion object {
        fun createIntent(context: Context): Intent {
            return Intent(context, LoginActivity::class.java)
        }
    }
}
