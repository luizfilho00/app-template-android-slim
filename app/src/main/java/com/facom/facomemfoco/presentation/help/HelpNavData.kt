package com.facom.facomemfoco.presentation.help

import android.content.Context
import android.content.Intent
import com.facom.facomemfoco.presentation.structure.navigation.NavData

class HelpNavData : NavData {
    override fun createIntent(context: Context): Intent {
        return HelpActivity.createIntent(context)
    }
}