package com.facom.facomemfoco.presentation.about

import android.content.Context
import android.content.Intent
import com.facom.facomemfoco.presentation.structure.navigation.NavData

class AboutNavData : NavData {
    override fun createIntent(context: Context): Intent {
        return AboutActivity.createIntent(context)
    }
}