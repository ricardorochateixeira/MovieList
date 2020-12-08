package com.ricardoteixeira.presentation.addmovie

import androidx.lifecycle.ViewModel
import com.ricardoteixeira.data.model.Movie
import com.ricardoteixeira.data.repository.MovieRepository
import com.ricardoteixeira.data.repository.MovieRepositoryImpl

class AddMovieViewModel(private val repository: MovieRepository = MovieRepositoryImpl()): ViewModel() {

    fun saveMovie(movie: Movie) {
        repository.saveMovie(movie)
    }

}