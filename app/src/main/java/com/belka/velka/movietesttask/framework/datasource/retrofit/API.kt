package com.belka.velka.movietesttask.framework.datasource.retrofit

import com.belka.velka.movietesttask.BuildConfig
import com.belka.velka.testmovie.core.domain.Film
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface API{
    @GET("master/movies.json")
    fun getMovies(): Call<List<Film>>

    companion object{
        fun getAPI(): API {
            val interceptor = HttpLoggingInterceptor().apply {
                HttpLoggingInterceptor.Level.BODY
            }

            val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

            val api = retrofit2.Retrofit.Builder().apply {
                addConverterFactory(GsonConverterFactory.create())
                baseUrl(BuildConfig.BASE_URL)
                client(client)
            }

            return api.build().create(API::class.java)
        }
    }
}