package com.example.moviewithrxjava.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.example.moviewithrxjava.model.MovieModel
import com.example.moviewithrxjava.network.MovieDBInterface
import com.example.moviewithrxjava.utility.Constraints
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class MovieDataSource(
    val apiService: MovieDBInterface,
    val compositeDisposable: CompositeDisposable
) : PageKeyedDataSource<Int, MovieModel>() {

    private var page = Constraints.FIRST_PAGE
    private val networkState: MutableLiveData<NetworkState> = MutableLiveData()


    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, MovieModel>) {
        networkState.postValue(NetworkState.Loading)
        compositeDisposable.add(
            apiService
                .getPopularMovies(params.key)
                .subscribeOn(Schedulers.io())
                .subscribe(
                    {
                       if (it.totalPages>=params.key){
                           callback.onResult(it.results,  params.key + 1)
                           networkState.postValue(NetworkState.Loaded)
                       }else{
                           networkState.postValue(NetworkState.EndOfList)
                       }
                    }, {
                        networkState.postValue(NetworkState.Failed)
                        Log.e("error","${it.message}")
                    }
                )
        )
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, MovieModel>) {

    }

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, MovieModel>
    ) {

        networkState.postValue(NetworkState.Loading)
        compositeDisposable.add(
            apiService
                .getPopularMovies(page)
                .subscribeOn(Schedulers.io())
                .subscribe(
                    {
                        callback.onResult(it.results, null, page + 1)
                        networkState.postValue(NetworkState.Loaded)
                    }, {
                        networkState.postValue(NetworkState.Failed)
                        Log.e("error","${it.message}")
                    }
                )
        )
    }
}