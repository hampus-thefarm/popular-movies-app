package online.hhapps.movieapp.api

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import online.hhapps.movieapp.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {
    private fun getRetrofit(): Retrofit {
        val okHttpClient = OkHttpClient.Builder().apply {
            addInterceptor(
                Interceptor { chain ->
                    val builder = chain.request().newBuilder()
                    builder.header("Authorization", "Bearer ${BuildConfig.bearerToken}")
                    return@Interceptor chain.proceed(builder.build())
                }
            )
        }.build()

        return Retrofit.Builder()
            .baseUrl(BuildConfig.apiURL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    val apiService: ApiService = getRetrofit().create(ApiService::class.java)
}