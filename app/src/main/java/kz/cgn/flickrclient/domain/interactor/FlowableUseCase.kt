package kz.cgn.flickrclient.domain.interactor

import io.reactivex.Flowable
import kz.cgn.flickrclient.domain.PostExecutionThread

abstract class FlowableUseCase<T, in Parameters>(private val schedulerProvider: PostExecutionThread) : UseCase<T>() {

    abstract fun buildUseCase(parameters: Parameters): Flowable<T>

    fun execute(onNext: (T) -> Unit, onError: (Throwable) -> Unit = {}, params: Parameters) {
        val flowable = buildUseCase(params).subscribeOn(schedulerProvider.io()).observeOn(schedulerProvider.ui())
        val disposable = flowable.subscribeWith(getDisposableSubscriber(onNext, onError))
        disposables.add(disposable)
    }

}