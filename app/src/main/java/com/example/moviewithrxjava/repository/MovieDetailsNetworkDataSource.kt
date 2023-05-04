package com.example.moviewithrxjava.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.moviewithrxjava.model.MovieDetailsModel
import com.example.moviewithrxjava.network.MovieDBInterface
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class MovieDetailsNetworkDataSource(
    private val apiService: MovieDBInterface,
    private val compositeDisposable: CompositeDisposable
) {

    private val _networkState = MutableLiveData<NetworkState>()
    val networkState: LiveData<NetworkState>
        get() = _networkState

    private val _downloadMovieDetailsResponse = MutableLiveData<MovieDetailsModel>()
    val downloadMovieDetailResponse: LiveData<MovieDetailsModel>
        get() = _downloadMovieDetailsResponse


    fun fetchMovieDetails(movieId: Int) {

        _networkState.postValue(NetworkState.Loading)

        try {
            compositeDisposable.add(
                apiService.getMovieDetails(movieId)
                    .subscribeOn(Schedulers.io())
                    .subscribe(
                        {
                        _downloadMovieDetailsResponse.postValue(it)
                            _networkState.postValue(NetworkState.Loaded)
                        },
                        {
                        _networkState.postValue(NetworkState.Failed)
                            Log.e("error","${it.message}")
                        },

                        )
            )
        } catch (e: Exception) {
            Log.e("error","${e.message}")

        }

    }
}