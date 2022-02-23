package online.hhapps.movieapp.ui.main.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.layout_movie_details_production_company.view.*
import online.hhapps.movieapp.BuildConfig
import online.hhapps.movieapp.R
import online.hhapps.movieapp.api.models.ProductionCompany

class MovieDetailsProductionCompaniesAdapter(private val productionCompanies: ArrayList<ProductionCompany>) : RecyclerView.Adapter<MovieDetailsProductionCompaniesAdapter.DataViewHolder>() {

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(productionCompany: ProductionCompany) {
            itemView.apply {
                Picasso.get().load("${BuildConfig.imageURL}${productionCompany.logo_path}").into(imageViewMovieDetailsProductionCompanyLogo)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder =
        DataViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.layout_movie_details_production_company, parent, false))

    override fun getItemCount(): Int = productionCompanies.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(productionCompanies[position])
    }

    fun addProductionCompanies(productionCompanies: List<ProductionCompany>) {
        this.productionCompanies.apply {
            clear()
            addAll(productionCompanies)
        }

    }
}