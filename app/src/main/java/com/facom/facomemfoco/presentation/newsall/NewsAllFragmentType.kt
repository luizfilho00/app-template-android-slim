package com.facom.facomemfoco.presentation.newsall

import com.facom.facomemfoco.presentation.structure.base.BaseFragment
import com.facom.facomemfoco.presentation.structure.navigation.FragmentType

class NewsAllFragmentType : FragmentType() {

    override fun retrieveFragment(): BaseFragment {
        return NewsAllFragment()
    }
}