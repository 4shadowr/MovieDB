package com.example.moviedb.services

import android.util.Log
import com.example.moviedb.api.MovieAPI
import com.example.moviedb.data.NetworkResult
import com.example.moviedb.data.remote.MovieResult
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieService @Inject constructor(
    private val movieApi: MovieAPI
) {
    private val TAG = "MovieService"

    suspend fun getMovies(page:Int) : NetworkResult<MovieResult> {

        try {
            val result = movieApi.getMovies(page)
            if(!result.isSuccessful) {
                return NetworkResult(false, null, "Something Went Wrong")
            }

            return NetworkResult(true, result.body())
        }
        catch (e: IOException){
            return NetworkResult(false, null, "No Internet Connection")
        }
        catch (e: Exception){
            Log.e(TAG, "getMovies: "+e.message,e)
            return NetworkResult(false, null, "Something Went Wrong")
        }



    }

}