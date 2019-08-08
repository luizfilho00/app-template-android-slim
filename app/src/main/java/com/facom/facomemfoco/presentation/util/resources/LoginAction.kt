package com.facom.facomemfoco.presentation.util.resources

import android.content.Context
import com.facom.facomemfoco.presentation.structure.navigation.Navigator

class LoginAction constructor(
        private val context: Context,
        private val cache: com.facom.facomemfoco.domain.boundary.resources.Cache
) {
    fun execute() {
        cache.clear()
        Navigator.goToLogin(context, true)
    }
}
