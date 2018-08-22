package kz.cgn.flickrclient.data

import io.reactivex.Completable
import io.reactivex.Flowable
import kz.cgn.flickrclient.data.persistence.IPersistenceRepository
import kz.cgn.flickrclient.data.persistence.model.SearchHistoryEntity
import kz.cgn.flickrclient.domain.ISearchHistoryRepository
import kz.cgn.flickrclient.domain.model.SearchHistory

class SearchHistoryRepositoryImpl(private val persistenceRepository: IPersistenceRepository): ISearchHistoryRepository {

    override fun getSearchHistory(tag: String): Flowable<List<SearchHistory>> {
        return persistenceRepository.getSearchHistory(tag).map { entityList -> entityList.map { it.convert() } }
    }


    override fun updateSearchHistory(tag: String) = persistenceRepository.updateSearchHistory(tag)

    override fun clearSearchHistory(): Completable {
        return persistenceRepository.clearSearchHistory()
    }
}

fun SearchHistoryEntity.convert(): SearchHistory = SearchHistory(tag)