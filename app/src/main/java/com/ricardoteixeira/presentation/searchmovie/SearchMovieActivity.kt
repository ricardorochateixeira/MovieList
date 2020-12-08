package com.ricardoteixeira.presentation.searchmovie

import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import androidx.lifecycle.Observer
import android.widget.ProgressBar
import androidx.appcompat.widget.Toolbar
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.ricardoteixeira.action
import com.ricardoteixeira.data.model.Movie
import com.ricardoteixeira.movielist.R
import com.ricardoteixeira.presentation.common.BaseActivity
import com.ricardoteixeira.presentation.main.MainActivity
import com.ricardoteixeira.snack
import org.jetbrains.anko.clearTask
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.newTask

class SearchMovieActivity: BaseActivity() {

    private val toolbar: Toolbar by lazy { findViewById<Toolbar>(R.id.toolbar_toolbar_view) }
    private lateinit var viewModel: SearchViewModel
    private var adapter = SearchListAdapter(mutableListOf()){ movie -> displayConfirmation(movie)}
    private lateinit var title: String

    override fun getToolbarInstance(): Toolbar? = toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        intent?.extras?.getString("title")?.let {
            title = it
        }
        viewModel = ViewModelProviders.of(this).get(SearchViewModel::class.java)
        searchMovie()
        val searchRecyclerView= findViewById<RecyclerView>(R.id.searchRecyclerView)
        searchRecyclerView.adapter = adapter
    }

    private fun searchMovie() {
        showLoading()
        viewModel.searchMovies(title).observe(this, Observer{ movies ->
            hideLoading()
            if (movies == null){
                showMessage()
            } else {
            adapter.setMovies(movies)
        }
        })
    }

    private fun showMessage(){
        val searchLayout = findViewById<ConstraintLayout>(R.id.searchLayout)
        searchLayout.snack(getString(R.string.network_error), Snackbar.LENGTH_INDEFINITE){
            action(getString(R.string.ok)) {
                searchMovie()
            }
        }
    }

    private fun showLoading(){
        val loading = findViewById<ProgressBar>(R.id.searchProgressBar)
        loading.visibility = View.VISIBLE
    }

    private fun hideLoading(){
        val loading = findViewById<ProgressBar>(R.id.searchProgressBar)
        loading.visibility = View.GONE
    }

    private fun displayConfirmation(movie: Movie) {
        val searchLayout = findViewById<ConstraintLayout>(R.id.searchLayout)
        searchLayout.snack("Add ${movie.title} to your list?", Snackbar.LENGTH_LONG) {
            action(getString(R.string.ok)){
                startActivity(intentFor<MainActivity>().newTask().clearTask())
                viewModel.saveMovie(movie)
            }
        }
    }
}