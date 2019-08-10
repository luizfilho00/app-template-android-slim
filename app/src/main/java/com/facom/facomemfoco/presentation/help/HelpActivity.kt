package com.facom.facomemfoco.presentation.help

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.facom.facomemfoco.R
import com.facom.facomemfoco.databinding.ActivityHelpBinding
import com.facom.facomemfoco.presentation.structure.base.BaseActivity
import com.facom.facomemfoco.presentation.structure.base.BaseViewModel
import com.facom.facomemfoco.presentation.structure.sl.ServiceLocator
import com.facom.facomemfoco.presentation.util.extensions.setupToolbar

class HelpActivity : BaseActivity() {

    override val sl: ServiceLocator get() = ServiceLocator.getInstance(this.applicationContext)
    override val baseViewModel: BaseViewModel get() = viewModel

    private lateinit var viewModel: HelpViewModel
    private lateinit var binding: ActivityHelpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        viewModel = sl.get(HelpViewModel::class.java)
        lifecycle.addObserver(viewModel)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_help)
        setupToolbar(binding.includedToolbar.toolbar, true, getString(R.string.activity_help_title))
        super.onCreate(savedInstanceState)
    }

    companion object {
        fun createIntent(context: Context): Intent {
            return Intent(context, HelpActivity::class.java)
        }
    }
}
