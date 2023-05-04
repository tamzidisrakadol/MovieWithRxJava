package com.example.moviewithrxjava.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.moviewithrxjava.model.MovieDetailsModel
import com.example.moviewithrxjava.repository.MovieDetailsRepository
import com.example.moviewithrxjava.repository.NetworkState
import io.reactivex.rxjava3.disposables.CompositeDisposable

class SingleMovieViewModel(private val movieDetailsRepository: MovieDetailsRepository, movieId:Int):ViewModel() {

    private val disposable=CompositeDisposable()

    val movieDetails:LiveData<MovieDetailsModel> by lazy {
        movieDetailsRepository.fetchingSingleMovieDetails(disposable,movieId)
    }

    val networkState:LiveData<NetworkState> by lazy{
        movieDetailsRepository.getMovieDetailNetworkResponse()
    }

    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
    }


}