package online.hhapps.movieapp.ui.main.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.layout_movie_details_genre.view.*
import online.hhapps.movieapp.R
import online.hhapps.movieapp.api.models.Genre

class MovieDetailsGenresAdapter(private val genres: ArrayList<Genre>) : RecyclerView.Adapter<MovieDetailsGenresAdapter.DataViewHolder>() {

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(genre: Genre) {
            itemView.apply {
                textViewMovieDetailsGenre.text = genre.name
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder =
        DataViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.layout_movie_details_genre, parent, false))

    override fun getItemCount(): Int = genres.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(genres[position])
    }

    fun addGenres(genres: List<Genre>) {
        this.genres.apply {
            clear()
            addAll(genres)
        }

    }
}