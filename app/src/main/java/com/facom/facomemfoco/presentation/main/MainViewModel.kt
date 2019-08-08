package com.facom.facomemfoco.presentation.main

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.OnLifecycleEvent
import com.facom.facomemfoco.domain.boundary.resources.SchedulerProvider
import com.facom.facomemfoco.presentation.structure.base.BaseViewModel

class MainViewModel(
        private val schedulerProvider: SchedulerProvider
) : BaseViewModel() {

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    private fun onCreate() {
        //TODO
    }
}