package com.ricardoteixeira.data.network

import com.ricardoteixeira.data.model.Movie
import com.ricardoteixeira.data.model.MoviesResponse
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {

    private val moviesApi: MoviesApi

    companion object {
        private const val API_KEY = "8a7064df5a9c9ce08342ff43b47464d0"
        private const val TMDB_BASE_URL = "http://api.themoviedb.org/3/"
        const val TMDB_IMAGEURL = "https://image.tmdb.org/t/p/w500/"
    }

    init {
        val builder = OkHttpClient.Builder()
        val okHttpClient = builder.build()
        val retrofit = Retrofit.Builder()
            .baseUrl(TMDB_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
        moviesApi = retrofit.create(MoviesApi::class.java)
    }

    fun searchMovies(query: String): Call<MoviesResponse> {
        return moviesApi.searchMovies(API_KEY, query)
    }
}