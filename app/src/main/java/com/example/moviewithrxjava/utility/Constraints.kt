package com.example.moviewithrxjava.utility

import com.example.moviewithrxjava.network.MovieDBInterface
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

object Constraints {

    const val apiKey = "db067c1f56bc503d02d3e091f69a3593"
    const val movieUrl = "https://api.themoviedb.org/3/"
    const val posterUrl = "https://image.tmdb.org/t/p/w342"


}