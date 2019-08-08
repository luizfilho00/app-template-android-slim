package com.facom.facomemfoco.domain.extensions

import com.facom.facomemfoco.domain.boundary.resources.SchedulerProvider
import com.facom.facomemfoco.presentation.util.viewmodels.Placeholder
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.subjects.PublishSubject

fun <T> Single<T>.defaultSched(schedulerProvider: SchedulerProvider): Single<T> {
    return this.subscribeOn(schedulerProvider.io()).observeOn(schedulerProvider.main())
}

fun <T> Single<T>.defaultConsumers(onSubscribeCallback: () -> (Unit), doAfterTerminatecallback: () -> (Unit)): Single<T> {
    return this.doOnSubscribe { onSubscribeCallback.invoke() }.doAfterTerminate { doAfterTerminatecallback.invoke() }
}

fun <T> Observable<T>.defaultConsumers(onSubscribeCallback: () -> (Unit), doAfterTerminatecallback: () -> (Unit)): Observable<T> {
    return this.doOnSubscribe { onSubscribeCallback.invoke() }.doAfterTerminate { doAfterTerminatecallback.invoke() }
}

fun <T> Observable<T>.defaultSched(schedulerProvider: SchedulerProvider): Observable<T> {
    return this.subscribeOn(schedulerProvider.io()).observeOn(schedulerProvider.main())
}

fun <T> PublishSubject<T>.defaultSched(schedulerProvider: SchedulerProvider): Observable<T> {
    return this.subscribeOn(schedulerProvider.io()).observeOn(schedulerProvider.main())
}

fun Completable.defaultSched(schedulerProvider: SchedulerProvider): Completable {
    return this.subscribeOn(schedulerProvider.io()).observeOn(schedulerProvider.main())
}

fun Completable.defaultPlaceholders(placeholderPlacerAction: (Placeholder) -> (Unit)): Completable {
    return this.defaultConsumers({ placeholderPlacerAction(Placeholder.Loading()) }, { placeholderPlacerAction(Placeholder.HideAll) })
}

fun Completable.defaultConsumers(onSubscribeCallback: () -> (Unit), doAfterTerminatecallback: () -> (Unit)): Completable {
    return this.doOnSubscribe { onSubscribeCallback.invoke() }.doAfterTerminate { doAfterTerminatecallback.invoke() }
}
