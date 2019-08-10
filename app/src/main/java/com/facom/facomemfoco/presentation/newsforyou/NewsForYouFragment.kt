package com.facom.facomemfoco.presentation.newsforyou

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.facom.facomemfoco.R
import com.facom.facomemfoco.databinding.FragmentNewsForYouBinding
import com.facom.facomemfoco.presentation.main.MainActivity
import com.facom.facomemfoco.presentation.structure.base.BaseFragment
import com.facom.facomemfoco.presentation.structure.base.BaseViewModel
import com.facom.facomemfoco.presentation.structure.sl.ServiceLocator

class NewsForYouFragment : BaseFragment() {

    override val sl: ServiceLocator get() = ServiceLocator.getInstance(context!!.applicationContext)
    private lateinit var viewModel: BaseViewModel
    private lateinit var binding: FragmentNewsForYouBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewModel = sl.get(NewsForYouViewModel::class.java)
        binding = FragmentNewsForYouBinding.inflate(inflater, container, false)
        lifecycle.addObserver(viewModel)
        setupActionBar()
        return binding.root
    }

    private fun setupActionBar() {
        (activity as? MainActivity)?.supportActionBar?.title = getString(R.string.fragment_for_you_title
        )
    }

    companion object {
        fun createIntent(context: Context): Intent {
            return Intent(context, NewsForYouFragment::class.java)
        }
    }
}
