package com.facom.facomemfoco.presentation.main

import com.facom.facomemfoco.domain.boundary.resources.SchedulerProvider
import com.facom.facomemfoco.presentation.structure.base.BaseViewModel

class MainViewModel(
        private val schedulerProvider: SchedulerProvider
) : BaseViewModel()