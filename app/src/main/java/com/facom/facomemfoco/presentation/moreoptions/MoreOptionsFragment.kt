package com.facom.facomemfoco.presentation.moreoptions

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import com.facom.facomemfoco.R
import com.facom.facomemfoco.databinding.FragmentMoreOptionsBinding
import com.facom.facomemfoco.presentation.main.MainActivity
import com.facom.facomemfoco.presentation.structure.base.BaseFragment
import com.facom.facomemfoco.presentation.structure.sl.ServiceLocator
import com.facom.facomemfoco.presentation.util.extensions.observeEvent
import com.facom.facomemfoco.presentation.util.extensions.onClickListener

class MoreOptionsFragment : BaseFragment() {

    override val sl: ServiceLocator get() = ServiceLocator.getInstance(context!!.applicationContext)

    private lateinit var viewModel: MoreOptionsViewModel
    private lateinit var binding: FragmentMoreOptionsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewModel = sl.get(MoreOptionsViewModel::class.java)
        binding = FragmentMoreOptionsBinding.inflate(inflater, container, false)
        lifecycle.addObserver(viewModel)
        setupActionBar()
        subscribeUi()
        setupUi()
        return binding.root
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        menu.findItem(R.id.action_search).isVisible = false
        super.onPrepareOptionsMenu(menu)
    }

    private fun subscribeUi() {
        with(viewModel) {
            goToAbout.observeEvent(this@MoreOptionsFragment, ::onGoTo)
        }
    }

    private fun setupActionBar() {
        (activity as? MainActivity)?.supportActionBar?.title = getString(R.string.fragment_more_options_title)
    }

    private fun setupUi() {
        with(binding) {
            buttonAbout.onClickListener(viewModel::goToAbout)
        }
    }

    companion object {
        fun createIntent(context: Context): Intent {
            return Intent(context, MoreOptionsFragment::class.java)
        }
    }
}
