package com.example.moviewithrxjava.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.moviewithrxjava.model.MovieModel
import com.example.moviewithrxjava.repository.MoviePagedListRepository
import com.example.moviewithrxjava.repository.NetworkState
import io.reactivex.rxjava3.disposables.CompositeDisposable

class PopularMovieViewModel(private val moviePagedListRepository: MoviePagedListRepository):ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    val moviePagedList :LiveData<PagedList<MovieModel>> by lazy {
        moviePagedListRepository.fetchLivePagedList(compositeDisposable)
    }

    val networkState :LiveData<NetworkState> by lazy {
        moviePagedListRepository.getNetworkState()
    }

    fun isEmptyList() : Boolean{
        return moviePagedList.value?.isEmpty()?:true
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}