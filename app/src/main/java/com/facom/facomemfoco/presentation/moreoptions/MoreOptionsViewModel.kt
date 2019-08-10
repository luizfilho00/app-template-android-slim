package com.facom.facomemfoco.presentation.moreoptions

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.facom.facomemfoco.domain.boundary.resources.SchedulerProvider
import com.facom.facomemfoco.presentation.about.AboutNavData
import com.facom.facomemfoco.presentation.structure.arch.Event
import com.facom.facomemfoco.presentation.structure.base.BaseViewModel
import com.facom.facomemfoco.presentation.structure.navigation.NavData

class MoreOptionsViewModel(
        private val schedulerProvider: SchedulerProvider
) : BaseViewModel() {

    val goToAbout: LiveData<Event<NavData>> get() = goToAboutLiveData

    private val goToAboutLiveData: MutableLiveData<Event<NavData>> = MutableLiveData()

    fun goToAbout() {
        goToAboutLiveData.value = Event(AboutNavData())
    }
}