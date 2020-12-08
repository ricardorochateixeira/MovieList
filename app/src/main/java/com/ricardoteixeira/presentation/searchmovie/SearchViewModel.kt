package com.ricardoteixeira.presentation.searchmovie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.ricardoteixeira.data.model.Movie
import com.ricardoteixeira.data.repository.MovieRepository
import com.ricardoteixeira.data.repository.MovieRepositoryImpl

class SearchViewModel(private val repository: MovieRepository = MovieRepositoryImpl()): ViewModel(){

    fun searchMovies(query: String): LiveData<List<Movie>?> {
        return repository.searchMovies(query)
    }

    fun saveMovie(movie: Movie) {
        repository.saveMovie(movie)
    }
}