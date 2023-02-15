package com.example.moviedb

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.moviedb.data.remote.Movie
import com.example.moviedb.services.MovieService

class MoviePagingSource(
    private val service: MovieService,
) : PagingSource<Int, Movie>()
{
    private val STARTING_PAGE_INDEX: Int = 1

    override suspend fun load(params: LoadParams<Int>):LoadResult<Int, Movie> {
        return try {
            val position = params.key ?: STARTING_PAGE_INDEX
            val response = service.getMovies(position)
            response.data!!.let {
                LoadResult.Page(
                    data = it.results,
                    prevKey = if(position == 1) null else position - 1,
                    nextKey = if(position == response.data.total_pages) null else position + 1
                )
            }
        }
        catch (e:Exception){
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1) ?:
            state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

}