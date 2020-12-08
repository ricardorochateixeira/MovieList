package com.ricardoteixeira.data.database

import android.app.Application
import androidx.room.*
import com.ricardoteixeira.data.model.Movie

@Database(entities = [Movie::class], version = 1)
@TypeConverters(GenreIdConverter::class)
abstract class MovieDatabase: RoomDatabase() {

    abstract fun movieDao(): MovieDao

    companion object {
        private val lock = Any()
        private val DATABASE_NAME = "MovieDatabase"
        private var INSTANCE: MovieDatabase? = null


        fun getInstance(application: Application): MovieDatabase {
            synchronized(lock) {
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(application, MovieDatabase::class.java, DATABASE_NAME).build()
                }
            }
            return INSTANCE!!
        }
    }


}