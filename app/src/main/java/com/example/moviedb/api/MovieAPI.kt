package com.example.moviedb.api

import retrofit2.Response
import retrofit2.http.GET

import com.example.moviedb.data.remote.Movie
import com.example.moviedb.data.remote.MovieResult
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieAPI {

    @GET("movie/now_playing?api_key=1d9b898a212ea52e283351e521e17871&language=en-US")
    suspend fun getMovies(
        @Query("page") page:Int = 1
    ): Response<MovieResult>
}