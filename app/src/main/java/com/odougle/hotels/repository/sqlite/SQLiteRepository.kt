package com.odougle.hotels.repository.sqlite

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import android.content.Context
import com.odougle.hotels.model.Hotel
import com.odougle.hotels.repository.HotelRepository


class SQLiteRepository(context: Context) : HotelRepository {

    private val helper = HotelSqlHelper(context)

    private fun insert(hotel: Hotel){
        val db = helper.writableDatabase
        val cv = ContentValues().apply {
            put(COLUMN_NAME, hotel.name)
            put(COLUMN_ADDRESS, hotel.address)
            put(COLUMN_RATING, hotel.rating)
        }
        val id = db.insert(TABLE_HOTEL, null, cv)
        if(id != -1L){
            hotel.id = id
        }
        db.close()
    }

    private fun update(hotel: Hotel){
        val db = helper.writableDatabase
        val cv = ContentValues().apply {
            put(COLUMN_ID, hotel.id)
            put(COLUMN_NAME, hotel.name)
            put(COLUMN_ADDRESS, hotel.address)
            put(COLUMN_RATING, hotel.rating)
        }
        db.insertWithOnConflict(
            TABLE_HOTEL,
            null,
            cv,
            SQLiteDatabase.CONFLICT_REPLACE)
        db.close()
    }

    override fun save(hotel: Hotel) {
        if(hotel.id == 0L){
            insert(hotel)
        }else{
            update(hotel)
        }
    }

    override fun remove(vararg hotels: Hotel) {
        val db = helper.writableDatabase
        for(hotel in hotels){
            db.delete(
                TABLE_HOTEL,
                "$COLUMN_ID = ?",
                arrayOf(hotel.id.toString())
            )
        }
        db.close()
    }

    override fun hotelById(id: Long, callback: (Hotel?) -> Unit) {
        TODO("Not yet implemented")
    }

    override fun search(term: String, callback: (List<Hotel>) -> Unit) {
        TODO("Not yet implemented")
    }


}