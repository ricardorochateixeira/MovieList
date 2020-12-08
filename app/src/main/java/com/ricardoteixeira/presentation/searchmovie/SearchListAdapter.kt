package com.ricardoteixeira.presentation.searchmovie

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.ricardoteixeira.data.model.Movie
import com.ricardoteixeira.data.network.RetrofitClient
import com.ricardoteixeira.movielist.R
import com.squareup.picasso.Picasso

class SearchListAdapter(
    private val movies: MutableList<Movie>,
    private var listener: (Movie) -> Unit
): RecyclerView.Adapter<SearchListAdapter.MovieHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie_search, parent, false)
        return MovieHolder(view)
    }

    override fun getItemCount(): Int = movies.size ?: 0

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        holder.bind(movies[position], position)
    }

    fun setMovies(movieList: List<Movie>){
        this.movies.clear()
        this.movies.addAll(movieList)
        notifyDataSetChanged()
    }

    inner class MovieHolder(val view: View):RecyclerView.ViewHolder(view){
        fun bind(movie: Movie, position: Int) = with(view) {
            val searchTitleTextView = findViewById<TextView>(R.id.searchTitleTextView)
            searchTitleTextView.text = movie.title

            val searchReleaseDateTextView = findViewById<TextView>(R.id.searchReleaseDateTextView)
            searchReleaseDateTextView.text = movie.releaseDate
            view.setOnClickListener { listener(movies?.get(position)) }

            val movieImage = findViewById<ImageView>(R.id.searchImageView)

            if (movie.posterPath != null){
                Picasso.get().load(RetrofitClient.TMDB_IMAGEURL + movie.posterPath).into(movieImage)
            } else {
                movieImage.setImageDrawable(ResourcesCompat.getDrawable(resources, R.drawable.ic_launcher_background, null))
            }
        }
    }
}



