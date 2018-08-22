package kz.cgn.flickrclient.domain.interactor

import io.reactivex.Completable
import kz.cgn.flickrclient.domain.ISearchHistoryRepository
import kz.cgn.flickrclient.domain.PostExecutionThread
import kz.cgn.flickrclient.domain.interactor.base.CompletableUseCase

class ClearSearchHistoryUserCase(postExecutionThread: PostExecutionThread,
                                 private val repository: ISearchHistoryRepository) : CompletableUseCase<Unit>(postExecutionThread) {

    override fun buildUseCase(parameters: Unit): Completable {
        return repository.clearSearchHistory()
    }
}