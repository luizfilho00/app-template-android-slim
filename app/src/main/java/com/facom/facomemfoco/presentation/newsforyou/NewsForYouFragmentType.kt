package com.facom.facomemfoco.presentation.newsforyou

import com.facom.facomemfoco.presentation.structure.base.BaseFragment
import com.facom.facomemfoco.presentation.structure.navigation.FragmentType

class NewsForYouFragmentType : FragmentType() {

    override fun retrieveFragment(): BaseFragment {
        return NewsForYouFragment()
    }
}