package com.facom.facomemfoco.presentation

import androidx.multidex.MultiDexApplication
import com.facebook.stetho.Stetho
import com.facom.facomemfoco.presentation.structure.sl.ServiceLocator

class AppTemplateSlimApplication : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        ServiceLocator.getInstance(this)
        Stetho.initializeWithDefaults(this)
    }
}
