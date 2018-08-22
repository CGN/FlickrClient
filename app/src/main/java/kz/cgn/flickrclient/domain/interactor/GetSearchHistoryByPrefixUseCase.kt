package kz.cgn.flickrclient.domain.interactor

import io.reactivex.Flowable
import kz.cgn.flickrclient.domain.ISearchHistoryRepository
import kz.cgn.flickrclient.domain.PostExecutionThread
import kz.cgn.flickrclient.domain.interactor.base.FlowableUseCase
import kz.cgn.flickrclient.domain.model.SearchHistory

class GetSearchHistoryByPrefixUseCase(postExecutionThread: PostExecutionThread,
                                      private val repository: ISearchHistoryRepository) : FlowableUseCase<List<SearchHistory>, String>(postExecutionThread) {

    override fun buildUseCase(tag: String): Flowable<List<SearchHistory>> {
        return repository.getSearchHistory(tag)
    }
}