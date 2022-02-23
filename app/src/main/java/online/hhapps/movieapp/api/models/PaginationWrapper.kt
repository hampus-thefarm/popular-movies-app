package online.hhapps.movieapp.api.models

data class PaginationWrapper<T>(
    var results: T? = null,
)