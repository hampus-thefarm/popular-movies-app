package online.hhapps.movieapp.api.models

data class MovieDetails(
    var backdrop_path: String,
    var genres: List<Genre>,
    var overview: String,
    var production_companies: List<ProductionCompany>,
    var spoken_languages: List<SpokenLanguage>,
    var title: String,
)