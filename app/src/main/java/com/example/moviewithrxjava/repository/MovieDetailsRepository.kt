package com.example.moviewithrxjava.repository

import androidx.lifecycle.LiveData
import com.example.moviewithrxjava.model.MovieDetailsModel
import com.example.moviewithrxjava.network.MovieDBInterface
import io.reactivex.rxjava3.disposables.CompositeDisposable

class MovieDetailsRepository(private val apiService:MovieDBInterface) {

    lateinit var movieDetailsNetworkDataSource: MovieDetailsNetworkDataSource

    fun fetchingSingleMovieDetails(compositeDisposable: CompositeDisposable,movieId:Int):LiveData<MovieDetailsModel>{

        movieDetailsNetworkDataSource = MovieDetailsNetworkDataSource(apiService,compositeDisposable)
        movieDetailsNetworkDataSource.fetchMovieDetails(movieId)
        return movieDetailsNetworkDataSource.downloadMovieDetailResponse
    }

    fun getMovieDetailNetworkResponse():LiveData<NetworkState>{
        return movieDetailsNetworkDataSource.networkState
    }


}