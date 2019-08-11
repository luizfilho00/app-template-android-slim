package com.facom.facomemfoco.presentation.interesttags

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.facom.facomemfoco.R
import com.facom.facomemfoco.databinding.ActivityInterestTagsBinding
import com.facom.facomemfoco.domain.entity.Tag
import com.facom.facomemfoco.presentation.structure.base.BaseActivity
import com.facom.facomemfoco.presentation.structure.base.BaseViewModel
import com.facom.facomemfoco.presentation.structure.sl.ServiceLocator
import com.facom.facomemfoco.presentation.util.extensions.observeEvent
import com.facom.facomemfoco.presentation.util.extensions.setupToolbar

class InterestTagsActivity : BaseActivity() {

    override val sl: ServiceLocator get() = ServiceLocator.getInstance(applicationContext)
    override val baseViewModel: BaseViewModel get() = viewModel

    private lateinit var viewModel: InterestTagsViewModel
    private lateinit var adapter: InterestTagsAdapter
    private lateinit var binding: ActivityInterestTagsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        viewModel = sl.get(InterestTagsViewModel::class.java)
        lifecycle.addObserver(viewModel)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_interest_tags)
        super.onCreate(savedInstanceState)
        setupToolbar(binding.includedToolbar.toolbar, true, getString(R.string.activity_interest_tags_title))
        setupUi()
        setupAdapter()
        setupRecyclerView()
    }

    override fun subscribeUi() {
        super.subscribeUi()
        with(viewModel) {
            listTagsLiveData.observeEvent(this@InterestTagsActivity, ::onNextListTags)
        }
    }

    private fun setupUi() {
        //TODO
    }

    private fun setupAdapter() {
        adapter = InterestTagsAdapter(viewModel::onTagClicked)
    }

    private fun setupRecyclerView() {
        with(binding.recyclerViewTags) {
            layoutManager = LinearLayoutManager(this@InterestTagsActivity)
            adapter = this@InterestTagsActivity.adapter
        }
    }

    private fun onNextListTags(tags: List<Tag>?) {
        tags?.run(adapter::setItems)
    }

    companion object {
        fun createIntent(context: Context): Intent {
            return Intent(context, InterestTagsActivity::class.java)
        }
    }
}
