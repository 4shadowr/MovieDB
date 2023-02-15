package com.example.moviedb.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.moviedb.repository.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.example.moviedb.data.remote.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val repo: MovieRepository
): ViewModel(){

    fun getMoviesPaging(): Flow<PagingData<Movie>> {
        return repo.getMovies().cachedIn(viewModelScope)
    }


}