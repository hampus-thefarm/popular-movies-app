package online.hhapps.movieapp.api

import online.hhapps.movieapp.api.models.MovieDetails
import online.hhapps.movieapp.api.models.PopularMovie
import online.hhapps.movieapp.api.models.PaginationWrapper
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("movie/popular")
    suspend fun getPopularMovies() : PaginationWrapper<List<PopularMovie>>

    @GET("movie/{id}")
    suspend fun getMovieDetails(@Path("id") movieId: Int) : MovieDetails
}