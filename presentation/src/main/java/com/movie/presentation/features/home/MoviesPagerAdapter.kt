package com.movie.presentation.features.home

import android.content.res.Resources
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.movie.common.inflate
import com.movie.domain.entities.Media
import com.movie.presentation.R
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_movie.*

class MoviesPagerAdapter(private val resources: Resources) : RecyclerView.Adapter<MoviesPagerAdapter.MoviePageHolder>() {

    private val cornerRadius by lazy { resources.getDimensionPixelSize(R.dimen.corner_home_poster) }

    var mediaList: List<Media> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviePageHolder {
        return MoviePageHolder(parent.inflate(R.layout.item_movie), cornerRadius)
    }

    override fun getItemCount(): Int = mediaList.size

    override fun onBindViewHolder(holder: MoviePageHolder, position: Int) {
        holder.bind(mediaList[position])
    }

    class MoviePageHolder(
        override val containerView: View,
        private val cornerRadius: Int
    ) : RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bind(media: Media) {
            Glide.with(movie_poster)
                .load(media.preview.source<String>())
                .transform()
                .transform(CenterCrop(), RoundedCorners(cornerRadius))
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(movie_poster)
            movie_title.text = media.title
            movie_genre.text = media.genres.joinToString { it.name }
        }

    }

}