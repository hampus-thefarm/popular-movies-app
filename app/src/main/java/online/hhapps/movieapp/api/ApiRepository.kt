package online.hhapps.movieapp.api

class ApiRepository(private val apiHelper: ApiHelper) {
    suspend fun getPopularMovies() = apiHelper.getPopularMovies()
    suspend fun getMovieDetails(movieId: Int) = apiHelper.getMovieDetails(movieId)
}