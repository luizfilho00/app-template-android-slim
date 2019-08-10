package com.facom.facomemfoco.presentation.about

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import com.facom.facomemfoco.R
import com.facom.facomemfoco.databinding.ActivityAboutBinding
import com.facom.facomemfoco.presentation.structure.base.BaseActivity
import com.facom.facomemfoco.presentation.structure.base.BaseViewModel
import com.facom.facomemfoco.presentation.structure.sl.ServiceLocator
import com.facom.facomemfoco.presentation.util.extensions.setupToolbar

class AboutActivity : BaseActivity() {

    override val sl: ServiceLocator get() = ServiceLocator.getInstance(this.applicationContext)
    override val baseViewModel: BaseViewModel get() = viewModel

    private lateinit var viewModel: AboutViewModel
    private lateinit var binding: ActivityAboutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        viewModel = sl.get(AboutViewModel::class.java)
        lifecycle.addObserver(viewModel)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_about)
        setupToolbar(binding.includedToolbar.toolbar, true, getString(R.string.activity_about_title))
        super.onCreate(savedInstanceState)
    }

    companion object {
        fun createIntent(context: Context): Intent {
            return Intent(context, AboutActivity::class.java)
        }
    }
}
