package com.odougle.hotels.repository.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.odougle.hotels.model.Hotel
import com.odougle.hotels.common.COLUMN_ID
import com.odougle.hotels.common.COLUMN_NAME
import com.odougle.hotels.common.TABLE_HOTEL


@Dao
interface HotelDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(hotel: Hotel): Long

    @Update
    fun update(hotel: Hotel): Int

    @Delete
    fun delete(vararg hotels: Hotel): Int

    @Query("""SELECT * FROM $TABLE_HOTEL WHERE $COLUMN_ID = :id""")
    fun hotelById(id: Long): LiveData<Hotel>

    @Query(
        """SELECT * FROM $TABLE_HOTEL 
        WHERE $COLUMN_NAME LIKE :query ORDER BY $COLUMN_NAME""")
    fun search(query: String): LiveData<List<Hotel>>
}