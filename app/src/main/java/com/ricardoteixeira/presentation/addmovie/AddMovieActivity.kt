package com.ricardoteixeira.presentation.addmovie

import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.ricardoteixeira.action
import com.ricardoteixeira.data.model.Movie
import com.ricardoteixeira.movielist.R
import com.ricardoteixeira.presentation.common.BaseActivity
import com.ricardoteixeira.presentation.searchmovie.SearchMovieActivity
import com.ricardoteixeira.snack
import org.jetbrains.anko.intentFor
import org.w3c.dom.Text

class AddMovieActivity: BaseActivity() {

    private val toolbar: Toolbar by lazy { findViewById<Toolbar>(R.id.toolbar_toolbar_view) }

    private lateinit var viewModel: AddMovieViewModel

    override fun getToolbarInstance(): Toolbar? = toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
        viewModel = ViewModelProviders.of(this).get(AddMovieViewModel::class.java)
        addMovie()
        buttonClicked()
    }

    private fun addMovie() {
        findViewById<Button>(R.id.addMovieButton).setOnClickListener {
            val movieTitle = findViewById<TextView>(R.id.titleEditText)
            val year = findViewById<TextView>(R.id.yearEditText)

            if (movieTitle.text.toString().isNotBlank()) {
                viewModel.saveMovie(Movie(title =movieTitle.text.toString(), releaseDate =  year.text.toString()))
            } else {
                showMessage(getString(R.string.enter_title))
            }
        }
    }

    private fun showMessage(msg: String){
        findViewById<View>(R.id.addLayout).snack((msg), Snackbar.LENGTH_LONG) {
            action(getString(R.string.ok)) {
            }
        }
    }

    private fun buttonClicked(){
        val searchView = findViewById<ImageView>(R.id.imageButton)
        searchView.setOnClickListener {
            searchMovies()
        }
    }

    private fun searchMovies(){
        val titleEditText = findViewById<TextView>(R.id.titleEditText)
        if (titleEditText.text.toString().isNotBlank()) {
            startActivity(intentFor<SearchMovieActivity>("title" to titleEditText.text.toString()))
        } else {
            showMessage(getString(R.string.enter_title))
        }
    }
}