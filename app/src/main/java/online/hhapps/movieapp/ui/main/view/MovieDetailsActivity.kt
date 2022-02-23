package online.hhapps.movieapp.ui.main.view

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_movie_details.*
import online.hhapps.movieapp.BuildConfig
import online.hhapps.movieapp.R
import online.hhapps.movieapp.api.ApiHelper
import online.hhapps.movieapp.api.RetrofitBuilder
import online.hhapps.movieapp.api.models.Genre
import online.hhapps.movieapp.api.models.MovieDetails
import online.hhapps.movieapp.api.models.ProductionCompany
import online.hhapps.movieapp.api.models.SpokenLanguage
import online.hhapps.movieapp.ui.main.MainViewModel
import online.hhapps.movieapp.ui.main.ViewModelFactory
import online.hhapps.movieapp.ui.main.adapters.MovieDetailsGenresAdapter
import online.hhapps.movieapp.ui.main.adapters.MovieDetailsProductionCompaniesAdapter
import online.hhapps.movieapp.ui.main.adapters.MovieDetailsSpokenLanguagesAdapter
import online.hhapps.movieapp.ui.main.decorators.HorizontalSpacingItemDecoration
import online.hhapps.movieapp.utils.Status


class MovieDetailsActivity : AppCompatActivity() {
    private var movieId = 0

    private lateinit var viewModel: MainViewModel

    private lateinit var genresAdapter: MovieDetailsGenresAdapter
    private lateinit var spokenLanguagesAdapter: MovieDetailsSpokenLanguagesAdapter
    private lateinit var productionCompaniesAdapter: MovieDetailsProductionCompaniesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        supportActionBar?.title = intent.getStringExtra("MOVIE_TITLE")
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        movieId = intent.getIntExtra("MOVIE_ID", 0)

        if (movieId == 0) {
            finish()
        }

        setupViewModel()
        setupUI()
        setupObservers()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }

        return super.onOptionsItemSelected(item)
    }

    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(ApiHelper(RetrofitBuilder.apiService))
        ).get(MainViewModel::class.java)
    }

    private fun setupUI() {
        recyclerViewMovieDetailsGenres.layoutManager = LinearLayoutManager(this)
        genresAdapter = MovieDetailsGenresAdapter(arrayListOf())
        recyclerViewMovieDetailsGenres.adapter = genresAdapter

        recyclerViewMovieDetailsSpokenLanguages.layoutManager = LinearLayoutManager(this)
        spokenLanguagesAdapter = MovieDetailsSpokenLanguagesAdapter(arrayListOf())
        recyclerViewMovieDetailsSpokenLanguages.adapter = spokenLanguagesAdapter

        recyclerViewMovieDetailsProductionCompanies.layoutManager = LinearLayoutManager(this)
        recyclerViewMovieDetailsProductionCompanies.addItemDecoration(
            HorizontalSpacingItemDecoration(32)
        )
        productionCompaniesAdapter = MovieDetailsProductionCompaniesAdapter(arrayListOf())
        recyclerViewMovieDetailsProductionCompanies.adapter = productionCompaniesAdapter
    }

    private fun setupContent(movieDetails: MovieDetails) {
        Picasso.get().load("${BuildConfig.imageURL}${movieDetails.backdrop_path}").into(
            imageViewMovieDetailsBackdrop
        )
        textViewMovieDetailsOverView.text = movieDetails.overview
        addGenres(movieDetails.genres)
        addSpokenLanguages(movieDetails.spoken_languages)
        addProductionCompanies(movieDetails.production_companies)
    }

    private fun setupObservers() {
        viewModel.getMovieDetails(movieId).observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        scrollViewMovieDetails.visibility = View.VISIBLE
                        progressBarMovieDetails.visibility = View.GONE
                        resource.data?.let { movieDetails -> setupContent(movieDetails) }
                    }
                    Status.ERROR -> {
                        scrollViewMovieDetails.visibility = View.VISIBLE
                        progressBarMovieDetails.visibility = View.GONE
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                        progressBarMovieDetails.visibility = View.VISIBLE
                        scrollViewMovieDetails.visibility = View.GONE
                    }
                }
            }
        })
    }

    private fun addGenres(genres: List<Genre>) {
        genresAdapter.apply {
            addGenres(genres)
            notifyDataSetChanged()
        }
    }

    private fun addSpokenLanguages(spokenLanguages: List<SpokenLanguage>) {
        spokenLanguagesAdapter.apply {
            addSpokenLanguages(spokenLanguages)
            notifyDataSetChanged()
        }
    }

    private fun addProductionCompanies(productionCompanies: List<ProductionCompany>) {
        productionCompaniesAdapter.apply {
            addProductionCompanies(productionCompanies)
            notifyDataSetChanged()
        }
    }
}