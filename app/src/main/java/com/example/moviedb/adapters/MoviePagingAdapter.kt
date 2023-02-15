package com.example.moviedb.adapters

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviedb.R
import com.example.moviedb.data.remote.Movie
import com.example.moviedb.utils.DateUtil
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class MoviePagingAdapter(
    private val context: Context
) : PagingDataAdapter<Movie, MoviePagingAdapter.MovieViewHolder>(MOVIE_COMPARATOR) {



    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: MovieViewHolder,
        position: Int
    ) {
        val item = getItem(position)
        if(item != null) {
            holder.bind(item, context)
        }
    }

    // Holds the views for adding it to image and text
    class MovieViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        private val title: TextView = itemView.findViewById(R.id.title_text)
        private val posterImage: ImageView = itemView.findViewById(R.id.poster_image)
        private val date: TextView = itemView.findViewById(R.id.date_text)
        private val desc: TextView = itemView.findViewById(R.id.desc_text)
        val nextArrow: ImageView = itemView.findViewById(R.id.detail_arrow)
        private val progressBar: ProgressBar = itemView.findViewById(R.id.progress_bar)
        private val percentText: TextView = itemView.findViewById(R.id.percent_text)

        val BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w220_and_h330_face/"

        fun bind(item:Movie, context:Context) {
            title.text = item.title
            date.text = item.release_date
            desc.text = item.overview
            progressBar.progress = (item.vote_average*10).toInt()
            progressBar.indeterminateDrawable.setTint(Color.RED)
            progressBar.secondaryProgressTintList = ColorStateList.valueOf(Color.RED)

            date.text = DateUtil.getFormattedDate(
                item.release_date,
                "yyyy-MM-dd",
                "MMM, dd yyyy"
            )

            percentText.text = DecimalFormat("#").format(item.vote_average*10) + "%"

            Glide.with(context)
                .load(BASE_IMAGE_URL+item.backdrop_path)
                .into(posterImage)
        }
    }


    companion object {
        private val MOVIE_COMPARATOR = object  : DiffUtil.ItemCallback<Movie>(){
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem == newItem
            }

        }
    }
}