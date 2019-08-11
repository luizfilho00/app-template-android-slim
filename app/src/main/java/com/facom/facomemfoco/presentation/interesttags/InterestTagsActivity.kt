package com.facom.facomemfoco.presentation.interesttags

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.facom.facomemfoco.R
import com.facom.facomemfoco.databinding.ActivityInterestTagsBinding
import com.facom.facomemfoco.presentation.structure.base.BaseActivity
import com.facom.facomemfoco.presentation.structure.base.BaseViewModel
import com.facom.facomemfoco.presentation.structure.sl.ServiceLocator
import com.facom.facomemfoco.presentation.util.extensions.setupToolbar

class InterestTagsActivity : BaseActivity() {

    override val sl: ServiceLocator get() = ServiceLocator.getInstance(applicationContext)
    override val baseViewModel: BaseViewModel get() = viewModel

    private lateinit var viewModel: InterestTagsViewModel
    private lateinit var binding: ActivityInterestTagsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        viewModel = sl.get(InterestTagsViewModel::class.java)
        lifecycle.addObserver(viewModel)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_interest_tags)
        setupToolbar(binding.includedToolbar.toolbar, true, getString(R.string.activity_interest_tags_title))
        super.onCreate(savedInstanceState)
    }

    companion object {
        fun createIntent(context: Context): Intent {
            return Intent(context, InterestTagsActivity::class.java)
        }
    }
}
