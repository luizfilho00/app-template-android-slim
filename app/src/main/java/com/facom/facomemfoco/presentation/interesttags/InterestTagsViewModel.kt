package com.facom.facomemfoco.presentation.interesttags

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.OnLifecycleEvent
import com.facom.facomemfoco.domain.boundary.resources.SchedulerProvider
import com.facom.facomemfoco.domain.entity.Tag
import com.facom.facomemfoco.domain.extensions.defaultSched
import com.facom.facomemfoco.domain.interactor.tags.GetAllTags
import com.facom.facomemfoco.presentation.structure.arch.Event
import com.facom.facomemfoco.presentation.structure.base.BaseViewModel
import com.facom.facomemfoco.presentation.util.extensions.defaultPlaceholders
import io.reactivex.rxkotlin.subscribeBy

class InterestTagsViewModel(
        private val schedulerProvider: SchedulerProvider,
        private val getAllTags: GetAllTags
) : BaseViewModel() {

    val listTagsLiveData: LiveData<Event<List<Tag>>> get() = _listTagsLiveData

    private val _listTagsLiveData: MutableLiveData<Event<List<Tag>>> = MutableLiveData()

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    private fun onCreate() {
        getAllTags.execute()
                .defaultSched(schedulerProvider)
                .defaultPlaceholders(this::setPlaceholder)
                .subscribeBy(::handleFailure, ::onTagsReceived)
                .let(disposables::add)
    }

    fun onTagSelected(tag: Tag) {
        Log.d("TAG", tag.name)
    }

    private fun onTagsReceived(tags: List<Tag>) {
        _listTagsLiveData.value = Event(tags)
    }

    private fun handleFailure(throwable: Throwable) {
        setDialog(throwable, ::onCreate)
    }
}