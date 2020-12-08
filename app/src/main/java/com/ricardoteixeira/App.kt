package com.ricardoteixeira

import android.app.Application
import com.ricardoteixeira.data.database.MovieDatabase
import com.ricardoteixeira.data.repository.MovieRepository
import com.ricardoteixeira.data.repository.MovieRepositoryImpl

lateinit var database: MovieDatabase

class App: Application() {

    companion object {
        lateinit var INSTANCE: App
    }

    init {
        INSTANCE = this
    }

    override fun onCreate() {
        super.onCreate()
        database = MovieDatabase.getInstance(this)
    }

    fun getMovieRepository(): MovieRepository = MovieRepositoryImpl()

}