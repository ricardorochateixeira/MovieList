package com.ricardoteixeira.data.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.ricardoteixeira.data.model.Movie

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(movie: Movie)

    @Query("SELECT * FROM movies")
    fun getAll(): LiveData<List<Movie>>

    @Query("DELETE FROM movies WHERE watched = :watched")
    fun deleteMovies(watched: Boolean)

    @Update
    fun updateMovie(movie: Movie)

    @Query("DELETE FROM movies WHERE id=:id")
    fun delete(id: Int?)
}