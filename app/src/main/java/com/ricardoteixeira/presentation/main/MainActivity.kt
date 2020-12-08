package com.ricardoteixeira.presentation.main

import android.content.Intent
import android.opengl.Visibility
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.ricardoteixeira.movielist.R
import com.ricardoteixeira.presentation.addmovie.AddMovieActivity
import com.ricardoteixeira.presentation.common.BaseActivity
import com.ricardoteixeira.presentation.searchmovie.SearchMovieActivity
import org.jetbrains.anko.startActivity

class MainActivity : BaseActivity() {

    private val toolbar: Toolbar by lazy { findViewById<Toolbar>(R.id.toolbar_toolbar_view) }
    private val adapter = MovieListAdapter(mutableListOf())
    private lateinit var viewModel: MainViewModel

    override fun getToolbarInstance(): Toolbar? = toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        showLoading()
        viewModel.getSavedMovies().observe(this, Observer { movies ->
            hideLoading()
            movies?.let {
                adapter.setMovies(movies)
            }
        })

        val moviesRecyclerView= findViewById<RecyclerView>(R.id.movieRecyclerView)
        moviesRecyclerView.adapter = adapter

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            goToAddActivity()
        }
    }

    private fun showLoading(){
        val loading = findViewById<ProgressBar>(R.id.progressBar)
        loading.visibility = View.VISIBLE
    }

    private fun hideLoading(){
        val loading = findViewById<ProgressBar>(R.id.progressBar)
        loading.visibility = View.GONE
    }


    private fun goToAddActivity() {
        val intent = Intent(this, AddMovieActivity::class.java)
        startActivity(intent)
    }
}