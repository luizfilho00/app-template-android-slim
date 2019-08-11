package com.facom.facomemfoco.presentation.moreoptions

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.facom.facomemfoco.domain.boundary.resources.SchedulerProvider
import com.facom.facomemfoco.presentation.about.AboutNavData
import com.facom.facomemfoco.presentation.help.HelpNavData
import com.facom.facomemfoco.presentation.interesttags.InterestTagsNavData
import com.facom.facomemfoco.presentation.login.LoginNavData
import com.facom.facomemfoco.presentation.structure.arch.Event
import com.facom.facomemfoco.presentation.structure.base.BaseViewModel
import com.facom.facomemfoco.presentation.structure.navigation.NavData

class MoreOptionsViewModel(
        private val schedulerProvider: SchedulerProvider
) : BaseViewModel() {

    val goToAbout: LiveData<Event<NavData>> get() = goToAboutLiveData
    val goToHelp: LiveData<Event<NavData>> get() = goToHelpLiveData
    val goToLogin: LiveData<Event<NavData>> get() = goToLoginLiveData
    val goToInterestTags: LiveData<Event<NavData>> get() = goToInterestTagsLiveData

    private val goToInterestTagsLiveData: MutableLiveData<Event<NavData>> = MutableLiveData()
    private val goToLoginLiveData: MutableLiveData<Event<NavData>> = MutableLiveData()
    private val goToHelpLiveData: MutableLiveData<Event<NavData>> = MutableLiveData()
    private val goToAboutLiveData: MutableLiveData<Event<NavData>> = MutableLiveData()

    internal fun goToAbout() {
        goToAboutLiveData.value = Event(AboutNavData())
    }

    internal fun goToHelp() {
        goToHelpLiveData.value = Event(HelpNavData())
    }

    internal fun goToLogin() {
        goToLoginLiveData.value = Event(LoginNavData())
    }

    internal fun goToInterestTags() {
        goToInterestTagsLiveData.value = Event(InterestTagsNavData())
    }
}