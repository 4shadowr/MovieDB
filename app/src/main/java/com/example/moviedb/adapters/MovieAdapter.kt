package com.example.moviedb.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviedb.R
import com.example.moviedb.data.remote.Movie
import java.text.DecimalFormat

class MovieAdapter(
    private val context: Context,
) : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    private var list: List<Movie> = ArrayList()

    val BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w220_and_h330_face/"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return ViewHolder(view)
    }

    fun submitList(videoList: List<Movie>){
        list = videoList
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.title.text = item.title
        holder.date.text = item.release_date
        holder.desc.text = item.overview
        holder.progressBar.progress = (item.vote_average*10).toInt()
        val df = DecimalFormat("#")
        holder.percentText.text = df.format(item.vote_average*10) + "%"

        Glide.with(context)
            .load(BASE_IMAGE_URL+item.backdrop_path)
            .into(holder.posterImage)
    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return list.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val posterImage: ImageView = itemView.findViewById(R.id.poster_image)
        val title: TextView = itemView.findViewById(R.id.title_text)
        val date: TextView = itemView.findViewById(R.id.date_text)
        val desc: TextView = itemView.findViewById(R.id.desc_text)
        val nextArrow: ImageView = itemView.findViewById(R.id.detail_arrow)
        val progressBar: ProgressBar = itemView.findViewById(R.id.progress_bar)
        val percentText: TextView = itemView.findViewById(R.id.percent_text)

    }
}
