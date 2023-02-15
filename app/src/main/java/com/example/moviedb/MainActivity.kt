package com.example.moviedb

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.example.moviedb.ui.MovieListFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        openNowPlayingFragment()
    }

    private fun openNowPlayingFragment(){
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            replace(R.id.fragment_container_view,MovieListFragment())
        }
        supportActionBar?.title = "Now Playing"
    }
}