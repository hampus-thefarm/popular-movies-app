package online.hhapps.movieapp.ui.main.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.layout_movie_details_spoken_language.view.*
import online.hhapps.movieapp.R
import online.hhapps.movieapp.api.models.SpokenLanguage

class MovieDetailsSpokenLanguagesAdapter(private val spokenLanguages: ArrayList<SpokenLanguage>) : RecyclerView.Adapter<MovieDetailsSpokenLanguagesAdapter.DataViewHolder>() {

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(spokenLanguage: SpokenLanguage) {
            itemView.apply {
                textViewMovieDetailsSpokenLanguage.text = spokenLanguage.name
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder =
        DataViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.layout_movie_details_spoken_language, parent, false))

    override fun getItemCount(): Int = spokenLanguages.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(spokenLanguages[position])
    }

    fun addSpokenLanguages(spokenLanguages: List<SpokenLanguage>) {
        this.spokenLanguages.apply {
            clear()
            addAll(spokenLanguages)
        }

    }
}