package com.ricardoteixeira.data.repository

import androidx.lifecycle.LiveData
import com.ricardoteixeira.data.model.Movie

interface MovieRepository {

    fun getSavedMovies(): LiveData<List<Movie>>

    fun saveMovie(movie: Movie)

    fun deleteMovie(movie: Movie)

    fun searchMovies(query: String): LiveData<List<Movie>?>
}