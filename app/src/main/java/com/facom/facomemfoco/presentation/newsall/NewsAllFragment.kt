package com.facom.facomemfoco.presentation.newsall

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.facom.facomemfoco.R
import com.facom.facomemfoco.databinding.FragmentNewsAllBinding
import com.facom.facomemfoco.presentation.main.MainActivity
import com.facom.facomemfoco.presentation.structure.base.BaseFragment
import com.facom.facomemfoco.presentation.structure.sl.ServiceLocator

class NewsAllFragment : BaseFragment() {

    override val sl: ServiceLocator get() = ServiceLocator.getInstance(context!!.applicationContext)
    private lateinit var viewModel: NewsAllViewModel
    private lateinit var binding: FragmentNewsAllBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewModel = sl.get(NewsAllViewModel::class.java)
        binding = FragmentNewsAllBinding.inflate(inflater, container, false)
        lifecycle.addObserver(viewModel)
        setupActionBar()
        return binding.root
    }

    private fun setupActionBar() {
        (activity as? MainActivity)?.supportActionBar?.title = getString(R.string.fragment_news_all_title)
    }

    companion object {
        fun createIntent(context: Context): Intent {
            return Intent(context, NewsAllFragment::class.java)
        }
    }
}
