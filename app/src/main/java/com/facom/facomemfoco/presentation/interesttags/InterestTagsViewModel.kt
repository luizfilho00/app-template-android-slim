package com.facom.facomemfoco.presentation.interesttags

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.OnLifecycleEvent
import com.facom.facomemfoco.domain.boundary.resources.SchedulerProvider
import com.facom.facomemfoco.domain.boundary.resources.StringsProvider
import com.facom.facomemfoco.domain.entity.Tag
import com.facom.facomemfoco.domain.extensions.defaultPlaceholders
import com.facom.facomemfoco.domain.extensions.defaultSched
import com.facom.facomemfoco.domain.interactor.tags.GetAllTags
import com.facom.facomemfoco.domain.interactor.tags.PersistTags
import com.facom.facomemfoco.presentation.structure.arch.Event
import com.facom.facomemfoco.presentation.structure.base.BaseViewModel
import com.facom.facomemfoco.presentation.util.extensions.defaultPlaceholders
import io.reactivex.rxkotlin.subscribeBy

class InterestTagsViewModel(
        private val schedulerProvider: SchedulerProvider,
        private val getAllTags: GetAllTags,
        private val persistTags: PersistTags,
        private val strings: StringsProvider
) : BaseViewModel() {

    val listTagsLiveData: LiveData<Event<List<Tag>>> get() = _listTagsLiveData

    private val _listTagsLiveData: MutableLiveData<Event<List<Tag>>> = MutableLiveData()
    private var listOfTags: MutableList<Tag> = mutableListOf()

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    private fun onCreate() {
        getAllTags.execute()
                .defaultSched(schedulerProvider)
                .defaultPlaceholders(::setPlaceholder)
                .subscribeBy(::handleFailure, ::onTagsReceived)
                .let(disposables::add)
    }

    fun onTagClicked(tag: Tag) {
        listOfTags.forEach {
            it.apply {
                if (name == tag.name) {
                    copy(
                            name = tag.name,
                            id = tag.id,
                            position = tag.position,
                            selected = tag.selected
                    )
                }
            }
        }
    }

    fun updateCache() {
        persistTags.execute(listOfTags.toList())
                .defaultSched(schedulerProvider)
                .defaultPlaceholders(::setPlaceholder)
                .subscribeBy(::handleFailure, ::onPersistedTags)
                .let(disposables::add)
    }

    private fun onTagsReceived(tags: List<Tag>) {
        listOfTags = tags.toMutableList()
        _listTagsLiveData.value = Event(tags)
    }

    private fun onPersistedTags() {
        setToast(strings.activityInterestTagsSaved)
    }

    private fun handleFailure(throwable: Throwable) {
        setDialog(throwable, ::onCreate)
    }
}