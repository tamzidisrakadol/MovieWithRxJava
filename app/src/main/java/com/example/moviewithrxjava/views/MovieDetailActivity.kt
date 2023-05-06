package com.example.moviewithrxjava.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.moviewithrxjava.R
import com.example.moviewithrxjava.model.MovieDetailsModel
import com.example.moviewithrxjava.network.MovieDBClient
import com.example.moviewithrxjava.repository.MovieDetailsRepository
import com.example.moviewithrxjava.viewModel.MovieDetailsModelFactory
import com.example.moviewithrxjava.viewModel.SingleMovieViewModel

class MovieDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        val movieId = 640146
        val apiService = MovieDBClient.getClient()
        val repository = MovieDetailsRepository(apiService)
        val viewModel = ViewModelProvider(this,MovieDetailsModelFactory(repository,movieId))[SingleMovieViewModel::class.java]

        viewModel.movieDetails.observe(this, Observer {
            Log.d("movie","title = ${it.title}")

        })
    }
}