package com.example.moviedb.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.moviedb.MoviePagingSource
import com.example.moviedb.data.NetworkResult
import com.example.moviedb.data.remote.MovieResult
import com.example.moviedb.services.MovieService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepository @Inject constructor(
    private val movieService: MovieService,
){
    fun getMovies() = Pager(
        config = PagingConfig(pageSize = 20),
        pagingSourceFactory = { MoviePagingSource(movieService) }
    ).flow

    suspend fun getMovies1(page: Int) : NetworkResult<MovieResult> {
        return movieService.getMovies(page)
    }

}