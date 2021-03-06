package com.odougle.hotels.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.odougle.hotels.common.COLUMN_ID
import com.odougle.hotels.common.TABLE_HOTEL

@Entity(tableName = TABLE_HOTEL)
data class Hotel(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = COLUMN_ID)
    var id: Long = 0,
    var name: String = "",
    var address: String = "",
    var rating: Float = 0.0F
) {
    override fun toString() = name
}