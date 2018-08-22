package kz.cgn.flickrclient.domain.interactor

import io.reactivex.Completable
import kz.cgn.flickrclient.domain.ISearchHistoryRepository
import kz.cgn.flickrclient.domain.PostExecutionThread
import kz.cgn.flickrclient.domain.interactor.base.CompletableUseCase

class UpdateSearchHistoryUseCase(postExecutionThread: PostExecutionThread,
                                 private val repository: ISearchHistoryRepository) : CompletableUseCase<String>(postExecutionThread) {

    override fun buildUseCase(parameters: String): Completable {
        return repository.updateSearchHistory(parameters)
    }
}