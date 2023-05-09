package com.example.moviewithrxjava.repository

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.moviewithrxjava.model.MovieModel
import com.example.moviewithrxjava.network.MovieDBInterface
import io.reactivex.rxjava3.disposables.CompositeDisposable

class MovieDataSourceFactory(private val apiService:MovieDBInterface, private val compositeDisposable: CompositeDisposable):DataSource.Factory<Int,MovieModel>() {

     val movieLiveDataSource = MutableLiveData<MovieDataSource>()

    override fun create(): DataSource<Int, MovieModel> {
        val movieDataSource = MovieDataSource(apiService,compositeDisposable)
        movieLiveDataSource.postValue(movieDataSource)
        return movieDataSource
    }
}