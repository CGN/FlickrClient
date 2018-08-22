package kz.cgn.flickrclient.data.persistence.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.util.*


@Entity(tableName = "searchHistory")
data class SearchHistoryEntity(
        @PrimaryKey(autoGenerate = true) var id: Long?,
        val tag: String,
        var lastUsed: Date = Date()
)