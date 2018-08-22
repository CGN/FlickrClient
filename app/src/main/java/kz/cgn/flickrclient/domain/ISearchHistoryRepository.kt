package kz.cgn.flickrclient.domain

import io.reactivex.Completable
import io.reactivex.Flowable
import kz.cgn.flickrclient.domain.model.SearchHistory

interface ISearchHistoryRepository {

    fun getSearchHistory(tag: String): Flowable<List<SearchHistory>>

    fun updateSearchHistory(tag: String): Completable

    fun clearSearchHistory(): Completable
}