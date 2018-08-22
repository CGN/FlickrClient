package kz.cgn.flickrclient.domain.interactor.base

import io.reactivex.Completable
import kz.cgn.flickrclient.domain.PostExecutionThread

abstract class CompletableUseCase<in Parameters>(private val schedulerProvider: PostExecutionThread) : UseCase<Unit>() {

    abstract fun buildUseCase(parameters: Parameters): Completable

    fun execute(onComplete: () -> Unit, onError: (Throwable) -> Unit = {}, params: Parameters) {
        val completable = buildUseCase(params).subscribeOn(schedulerProvider.io()).observeOn(schedulerProvider.ui())
        val disposable = completable.subscribeWith(getDisposableCompletableObserver(onComplete, onError))
        disposables.add(disposable)
    }

}