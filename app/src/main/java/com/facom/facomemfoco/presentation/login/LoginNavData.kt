package com.facom.facomemfoco.presentation.login

import android.content.Context
import android.content.Intent
import com.facom.facomemfoco.presentation.structure.navigation.NavData

class LoginNavData : NavData {
    override fun createIntent(context: Context): Intent {
        return LoginActivity.createIntent(context)
    }
}