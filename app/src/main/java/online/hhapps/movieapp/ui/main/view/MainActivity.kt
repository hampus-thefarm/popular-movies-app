package online.hhapps.movieapp.ui.main.view

import online.hhapps.movieapp.ui.main.adapters.PopularMoviesAdapter
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import online.hhapps.movieapp.R
import online.hhapps.movieapp.api.ApiHelper
import online.hhapps.movieapp.api.RetrofitBuilder
import online.hhapps.movieapp.api.models.PopularMovie
import online.hhapps.movieapp.ui.main.MainViewModel
import online.hhapps.movieapp.ui.main.ViewModelFactory
import online.hhapps.movieapp.utils.Status

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: PopularMoviesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.title = getString(R.string.popular_movies_activity_title)
        setupViewModel()
        setupUI()
        setupObservers()
    }

    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(ApiHelper(RetrofitBuilder.apiService))
        ).get(MainViewModel::class.java)
    }

    private fun setupUI() {
        recyclerViewPopularMovies.layoutManager = LinearLayoutManager(this)
        adapter = PopularMoviesAdapter(arrayListOf())
        recyclerViewPopularMovies.adapter = adapter
    }

    private fun setupObservers() {
        viewModel.getPopularMovies().observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        recyclerViewPopularMovies.visibility = View.VISIBLE
                        progressBarPopularMovies.visibility = View.GONE
                        resource.data?.results?.let { popularMovies -> retrieveList(popularMovies.take(10)) }
                    }
                    Status.ERROR -> {
                        recyclerViewPopularMovies.visibility = View.VISIBLE
                        progressBarPopularMovies.visibility = View.GONE
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                        progressBarPopularMovies.visibility = View.VISIBLE
                        recyclerViewPopularMovies.visibility = View.GONE
                    }
                }
            }
        })
    }

    private fun retrieveList(popularMovies: List<PopularMovie>) {
        adapter.apply {
            addPopularMovies(popularMovies)
            notifyDataSetChanged()
        }
    }
}
