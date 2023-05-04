package com.example.moviewithrxjava.network

import com.example.moviewithrxjava.model.MovieDetailsModel
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieDBInterface {


    @GET("movie/{movie_id}")
    fun getMovieDetails(@Path("movie_id")id:Int):Single<MovieDetailsModel>
}