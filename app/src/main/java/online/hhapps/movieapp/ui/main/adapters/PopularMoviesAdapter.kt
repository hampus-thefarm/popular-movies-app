package online.hhapps.movieapp.ui.main.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.layout_popular_movie.view.*
import online.hhapps.movieapp.BuildConfig
import online.hhapps.movieapp.R
import online.hhapps.movieapp.api.models.PopularMovie
import online.hhapps.movieapp.ui.main.view.MovieDetailsActivity

class PopularMoviesAdapter(private val popularMovies: ArrayList<PopularMovie>) : RecyclerView.Adapter<PopularMoviesAdapter.DataViewHolder>() {

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(popularMovie: PopularMovie) {
            itemView.apply {
                Picasso.get().load("${BuildConfig.imageURL}${popularMovie.poster_path}").into(imageViewPopularMoviePoster)

                this.setOnClickListener {
                    val movieDetailIntent = Intent(this.context, MovieDetailsActivity::class.java).apply {
                        putExtra("MOVIE_ID", popularMovie.id)
                        putExtra("MOVIE_TITLE", popularMovie.title)
                    }

                    this.context.startActivity(movieDetailIntent)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder =
        DataViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.layout_popular_movie, parent, false))

    override fun getItemCount(): Int = popularMovies.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(popularMovies[position])
    }

    fun addPopularMovies(popularMovies: List<PopularMovie>) {
        this.popularMovies.apply {
            clear()
            addAll(popularMovies)
        }

    }
}