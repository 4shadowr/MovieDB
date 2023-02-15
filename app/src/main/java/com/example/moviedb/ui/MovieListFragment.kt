package com.example.moviedb.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviedb.R
import com.example.moviedb.adapters.MovieAdapter
import com.example.moviedb.adapters.MoviePagingAdapter
import com.example.moviedb.viewModel.MovieListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MovieListFragment : Fragment() {

    private val TAG: String = "MovieListFragment"
    private val movieViewModel:MovieListViewModel by viewModels()

    private lateinit var recyclerView: RecyclerView
    private lateinit var videoAdapter: MovieAdapter

    private lateinit var newVideoPagingAdapter: MoviePagingAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.setHasFixedSize(true)

        activity?.actionBar?.title = "Now Playing"

        attachPaging()
    }


    fun attachPaging(){
        newVideoPagingAdapter = MoviePagingAdapter(requireContext())
        recyclerView.adapter = newVideoPagingAdapter

        lifecycleScope.launch{
            movieViewModel.getMoviesPaging().collectLatest{
                newVideoPagingAdapter.submitData(it)
            }
        }


    }


}