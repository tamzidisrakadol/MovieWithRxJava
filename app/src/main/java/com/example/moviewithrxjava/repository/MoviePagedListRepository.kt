package com.example.moviewithrxjava.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.switchMap

import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.moviewithrxjava.model.MovieModel
import com.example.moviewithrxjava.network.MovieDBInterface
import com.example.moviewithrxjava.utility.Constraints
import com.google.ar.core.Config
import io.reactivex.rxjava3.disposables.CompositeDisposable

class MoviePagedListRepository(private val apiService:MovieDBInterface) {

    lateinit var moviePagedList:LiveData<PagedList<MovieModel>>
    lateinit var movieDataSourceFactory: MovieDataSourceFactory

    fun fetchLivePagedList(compositeDisposable: CompositeDisposable):LiveData<PagedList<MovieModel>>{

        movieDataSourceFactory = MovieDataSourceFactory(apiService,compositeDisposable)

        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(Constraints.POST_PER_PAGE)
            .build()
        moviePagedList = LivePagedListBuilder(movieDataSourceFactory,config).build()
        return moviePagedList
    }


    fun getNetworkState(): LiveData<NetworkState> {
        return movieDataSourceFactory.movieLiveDataSource.switchMap {
            it.networkState
        }
    }

}