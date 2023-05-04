package com.example.moviewithrxjava.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.moviewithrxjava.repository.MovieDetailsRepository

@Suppress("UNCHECKED_CAST")
class MovieDetailsModelFactory(private val repository: MovieDetailsRepository, private val movieId:Int):ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SingleMovieViewModel(repository,movieId) as T
    }
}