package com.ricardoteixeira.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ricardoteixeira.data.database.MovieDao
import com.ricardoteixeira.data.model.Movie
import com.ricardoteixeira.data.model.MoviesResponse
import com.ricardoteixeira.data.network.RetrofitClient
import com.ricardoteixeira.database
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.concurrent.thread

class MovieRepositoryImpl: MovieRepository {

    private val movieDao: MovieDao = database.movieDao()
    private val retrofitClient = RetrofitClient()
    private val allMovies: LiveData<List<Movie>>

    init {
        allMovies = movieDao.getAll()
    }

    override fun getSavedMovies(): LiveData<List<Movie>> = allMovies

    override fun saveMovie(movie: Movie) {
        thread {
            movieDao.insert(movie)
        }
    }

    override fun deleteMovie(movie: Movie) {
        thread {
            movieDao.delete(movie.id)
        }
    }

    override fun searchMovies(query: String): LiveData<List<Movie>?> {
        val data = MutableLiveData<List<Movie>>()

        retrofitClient.searchMovies(query).enqueue(object: Callback<MoviesResponse> {
            override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
                data.value = null
                Log.d(this.javaClass.simpleName, "Failure")
            }

            override fun onResponse(
                call: Call<MoviesResponse>,
                response: Response<MoviesResponse>
            ) {
                data.value = response.body()?.results
                Log.d(this.javaClass.simpleName, "Response: ${response.body()?.results}")
            }
        })
        return data
    }
}