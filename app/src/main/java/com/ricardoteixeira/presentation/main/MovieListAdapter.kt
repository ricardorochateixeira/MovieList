package com.ricardoteixeira.presentation.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.ricardoteixeira.data.model.Movie
import com.ricardoteixeira.data.network.RetrofitClient
import com.ricardoteixeira.movielist.R
import com.squareup.picasso.Picasso

class MovieListAdapter(private val movies: MutableList<Movie>):
    RecyclerView.Adapter<MovieListAdapter.MovieHolder>() {

    val selectedMovies = HashSet<Movie>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie_main, parent, false)
        return MovieHolder(view)
    }

    override fun getItemCount(): Int = movies.size ?: 0

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        holder.bind(movies[position])
    }

    fun setMovies(movieList: List<Movie>){
        this.movies.clear()
        this.movies.addAll(movieList)
        notifyDataSetChanged()
    }

    inner class MovieHolder(val view: View): RecyclerView.ViewHolder(view) {

        fun bind(movie: Movie) = with(view) {

            val movieTitleTextView = findViewById<TextView>(R.id.movieTitleTextView)
            movieTitleTextView.text = movie.title

            val movieReleaseDateTextView = findViewById<TextView>(R.id.movieReleaseDateTextView)
            movieReleaseDateTextView.text = movie.releaseDate

            val checkBox = findViewById<CheckBox>(R.id.checkBox)
            checkBox.isChecked = movie.watched

            val movieImageView = findViewById<ImageView>(R.id.movieImageView)

            if (movie.posterPath != null){
                Picasso.get().load(RetrofitClient.TMDB_IMAGEURL + movie.posterPath).into(movieImageView)
            } else {
                movieImageView.setImageDrawable(ResourcesCompat.getDrawable(resources, R.drawable.ic_launcher_background, null))
            }

            checkBox.setOnCheckedChangeListener { checkBox, isChecked ->
                if(!selectedMovies.contains(movie) && isChecked) {
                    selectedMovies.add(movie)
                } else {
                    selectedMovies.remove(movie)
                }
            }
        }
}
}