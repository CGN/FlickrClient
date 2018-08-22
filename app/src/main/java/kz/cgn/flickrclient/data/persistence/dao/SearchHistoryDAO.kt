package kz.cgn.flickrclient.data.persistence.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query
import android.arch.persistence.room.Update
import io.reactivex.Completable
import io.reactivex.Flowable
import kz.cgn.flickrclient.data.persistence.model.SearchHistoryEntity

@Dao
interface SearchHistoryDAO {

    @Query("SELECT * FROM searchHistory WHERE lower(tag) like lower(:tag)||'%' ORDER BY lastUsed DESC")
    fun getAll(tag: String): Flowable<List<SearchHistoryEntity>>

    @Insert(onConflict = REPLACE)
    fun insert(searchHistoryEntity: SearchHistoryEntity)

    @Query("DELETE from searchHistory")
    fun deleteAll()

    @Query("SELECT * FROM searchHistory WHERE lower(tag) = lower(:tag)")
    fun find(tag: String): SearchHistoryEntity?

    @Update
    fun update(searchHistoryEntity: SearchHistoryEntity)
}