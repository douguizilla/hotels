package com.odougle.hotels.repository.sqlite

import android.content.Context
import android.database.sqlite.SQLiteOpenHelper

class HotelSqlHelper(context: Context)
    : SQLiteOpenHelper(context, DATABASE_NAME,null, DATABASE_VERSION){

}