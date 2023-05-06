package com.example.moviewithrxjava.network

import com.example.moviewithrxjava.model.MovieDetailsModel
import com.example.moviewithrxjava.model.MovieResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieDBInterface {

    @GET("movie/popular")
    fun getPopularMovies(@Query("page")page:Int):Single<MovieResponse>

    @GET("movie/{movie_id}")
    fun getMovieDetails(@Path("movie_id")id:Int):Single<MovieDetailsModel>
}