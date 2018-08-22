package kz.cgn.flickrclient.data.persistence

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import kz.cgn.flickrclient.data.persistence.dao.SearchHistoryDAO
import kz.cgn.flickrclient.data.persistence.model.SearchHistoryEntity
import kz.cgn.flickrclient.data.persistence.util.DateConverter

@Database(entities = arrayOf(SearchHistoryEntity::class), version = 1)
@TypeConverters(DateConverter::class)
abstract class AppDatabase: RoomDatabase() {
    abstract fun searchHistoryDao() : SearchHistoryDAO
}