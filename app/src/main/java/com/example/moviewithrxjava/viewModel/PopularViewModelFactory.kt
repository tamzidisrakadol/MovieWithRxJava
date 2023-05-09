package com.example.moviewithrxjava.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.moviewithrxjava.repository.MoviePagedListRepository

@Suppress("UNCHECKED_CAST")
class PopularViewModelFactory(val repository: MoviePagedListRepository):ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PopularMovieViewModel(repository) as T
    }
}