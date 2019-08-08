package com.facom.facomemfoco.presentation.password

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import com.facom.facomemfoco.R
import com.facom.facomemfoco.databinding.ActivityRecoverPasswordBinding
import com.facom.facomemfoco.presentation.structure.base.BaseActivity
import com.facom.facomemfoco.presentation.structure.base.BaseViewModel
import com.facom.facomemfoco.presentation.structure.sl.ServiceLocator
import com.facom.facomemfoco.presentation.util.extensions.*
import com.facom.facomemfoco.presentation.util.viewmodels.Placeholder


class RecoverPasswordActivity : BaseActivity() {
    override val sl: ServiceLocator get() = ServiceLocator.getInstance(this.applicationContext)
    override val baseViewModel: BaseViewModel get() = viewModel


    private lateinit var binding: ActivityRecoverPasswordBinding
    private lateinit var viewModel: RecoverPasswordViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        viewModel = sl.get(RecoverPasswordViewModel::class.java)
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_recover_password)
        binding.submitButton.setOnClickListener { viewModel.onSubmitButtonClick() }
        binding.emailInput.observeChanges(viewModel::onEmailChanged)
        showHomeButton()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_send, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_send) {
            viewModel.onSubmitButtonClick()
        }
        return handleMenuItemClick(item) || super.onOptionsItemSelected(item)
    }

    override fun subscribeUi() {
        super.subscribeUi()
        with(viewModel) {
            showEmailFieldError.observeEvent(this@RecoverPasswordActivity) { showEmailFieldError() }
            placeholder.observe(this@RecoverPasswordActivity, ::onNextPlaceholder)
            close.observeEvent(this@RecoverPasswordActivity) { if (it == true) close() }
        }
    }


    private fun onNextPlaceholder(placeholder: Placeholder?) {
        placeholder?.let { binding.includedLoading.placeholder = it }
    }

    private fun showEmailFieldError() {
        binding.emailInput.setError(R.string.validation_email)
    }

    private fun close() {
        finish()
    }

    companion object {

        fun createIntent(context: Context): Intent {
            return Intent(context, RecoverPasswordActivity::class.java)
        }
    }
}