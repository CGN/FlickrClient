package kz.cgn.flickrclient.data.persistence

import io.reactivex.Completable
import io.reactivex.Flowable
import kz.cgn.flickrclient.data.persistence.model.SearchHistoryEntity

interface IPersistenceRepository {

    fun getSearchHistory(tag: String): Flowable<List<SearchHistoryEntity>>

    fun updateSearchHistory(tag: String): Completable

    fun clearSearchHistory(): Completable
}