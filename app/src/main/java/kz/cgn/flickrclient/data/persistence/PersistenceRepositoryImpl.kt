package kz.cgn.flickrclient.data.persistence

import io.reactivex.Completable
import io.reactivex.Flowable
import kz.cgn.flickrclient.data.persistence.model.SearchHistoryEntity
import java.util.*

class PersistenceRepositoryImpl(private val appDatabase: AppDatabase) : IPersistenceRepository {

    override fun getSearchHistory(tag: String): Flowable<List<SearchHistoryEntity>> = appDatabase.searchHistoryDao().getAll(tag)

    override fun updateSearchHistory(tag: String): Completable {
        return Completable.fromAction {
            val searchHistoryEntity = appDatabase.searchHistoryDao().find(tag)
            if (searchHistoryEntity == null) {
                appDatabase.searchHistoryDao().insert(SearchHistoryEntity(id = null, tag = tag))
            } else {
                searchHistoryEntity.lastUsed = Date()
                appDatabase.searchHistoryDao().update(searchHistoryEntity)
            }
        }
    }

    override fun clearSearchHistory(): Completable {
        return Completable.fromAction { appDatabase.searchHistoryDao().deleteAll() }
    }
}