package com.facom.facomemfoco.presentation.moreoptions

import com.facom.facomemfoco.presentation.structure.base.BaseFragment
import com.facom.facomemfoco.presentation.structure.navigation.FragmentType

class MoreOptionsFragmentType : FragmentType() {

    override fun retrieveFragment(): BaseFragment {
        return MoreOptionsFragment()
    }
}