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
import com.facom.facomemfoco.presentation.moreoptions.MoreOptionsFragment
import com.facom.facomemfoco.presentation.moreoptions.MoreOptionsFragmentType
import com.facom.facomemfoco.presentation.newsall.NewsAllFragment
import com.facom.facomemfoco.presentation.newsall.NewsAllFragmentType
import com.facom.facomemfoco.presentation.newsforyou.NewsForYouFragment
import com.facom.facomemfoco.presentation.newsforyou.NewsForYouFragmentType
import com.facom.facomemfoco.presentation.structure.base.BaseActivity
import com.facom.facomemfoco.presentation.structure.base.BaseFragment
import com.facom.facomemfoco.presentation.structure.base.BaseViewModel
import com.facom.facomemfoco.presentation.structure.navigation.FragmentType
import com.facom.facomemfoco.presentation.structure.sl.ServiceLocator
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : BaseActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    override val sl: ServiceLocator get() = ServiceLocator.getInstance(this.applicationContext)
    override val baseViewModel: BaseViewModel get() = viewModel

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        viewModel = sl.get(MainViewModel::class.java)
        lifecycle.addObserver(viewModel)
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setupUi()
        /** Initial fragment **/
        resolveFragment(NewsForYouFragmentType())
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        return menu?.run {
            menuInflater.inflate(R.menu.menu_search_bar, menu)
            true
        } ?: false
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_search -> {
                openSearchField(item.actionView as? SearchView)
                true
            }
            else -> false
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.navigation_for_you -> resolveFragment(NewsForYouFragmentType())
            R.id.navigation_all -> resolveFragment(NewsAllFragmentType())
            R.id.navigation_more_options -> resolveFragment(MoreOptionsFragmentType())
        }
        return true
    }

    private fun setupUi() {
        with(binding) {
            bottomNavigationMenu.setOnNavigationItemSelectedListener(this@MainActivity)
        }
    }

    private fun resolveFragment(tag: FragmentType) {
        when (tag.retrieveFragment()) {
            is MoreOptionsFragment -> showFragment(MoreOptionsFragment())
            is NewsForYouFragment -> showFragment(NewsForYouFragment())
            is NewsAllFragment -> showFragment(NewsAllFragment())
        }
    }

    private fun showFragment(baseFragment: BaseFragment) {
        supportFragmentManager
                .beginTransaction()
                .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                .replace(R.id.fragment_holder, baseFragment)
                .commit()
    }

    private fun openSearchField(searchView: SearchView?) {
        searchView?.run {
            //TODO pegar texto e fazer busca na api (:
        }
    }

    companion object {
        fun createIntent(context: Context): Intent {
            return Intent(context, MainActivity::class.java)
        }
    }
}
