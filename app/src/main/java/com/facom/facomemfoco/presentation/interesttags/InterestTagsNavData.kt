package com.facom.facomemfoco.presentation.interesttags

import android.content.Context
import android.content.Intent
import com.facom.facomemfoco.presentation.structure.navigation.NavData

class InterestTagsNavData : NavData {
    override fun createIntent(context: Context): Intent {
        return InterestTagsActivity.createIntent(context)
    }
}