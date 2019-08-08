package com.facom.facomemfoco.presentation.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import com.facom.facomemfoco.R
import com.facom.facomemfoco.databinding.ActivityMainBinding
import com.facom.facomemfoco.presentation.structure.base.BaseActivity
import com.facom.facomemfoco.presentation.structure.base.BaseViewModel
import com.facom.facomemfoco.presentation.structure.sl.ServiceLocator
import com.facom.facomemfoco.presentation.util.extensions.longToast

class MainActivity : BaseActivity() {

    override val sl: ServiceLocator get() = ServiceLocator.getInstance(this.applicationContext)
    override val baseViewModel: BaseViewModel get() = viewModel

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        viewModel = sl.get(MainViewModel::class.java)
        lifecycle.addObserver(viewModel)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setupUi()
        setupActionBar()
        super.onCreate(savedInstanceState)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        return menu?.run {
            menuInflater.inflate(R.menu.menu_search_bar, menu)
            true
        } ?: false
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when {
            item?.itemId == R.id.action_search -> {
                openSearchField(item.actionView as? SearchView)
                true
            }
            else -> false
        }
    }

    private fun setupUi() {
        //TODO
    }

    private fun openSearchField(searchView: SearchView?) {
        searchView?.run {
            //TODO pegar texto e fazer busca na api (:
        }
    }

    private fun setupActionBar() {
        supportActionBar?.run {
            title = getString(R.string.global_action_bar_title)
        }
    }

    companion object {
        fun createIntent(context: Context): Intent {
            return Intent(context, MainActivity::class.java)
        }
    }
}
