package online.hhapps.movieapp.api

class ApiHelper(private val apiService: ApiService) {
    suspend fun getPopularMovies() = apiService.getPopularMovies()
    suspend fun getMovieDetails(movieId: Int) = apiService.getMovieDetails(movieId)
}