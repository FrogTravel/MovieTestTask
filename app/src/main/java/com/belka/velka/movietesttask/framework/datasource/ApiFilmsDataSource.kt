package com.belka.velka.movietesttask.framework.datasource

import com.belka.velka.movietesttask.framework.datasource.retrofit.API
import com.belka.velka.testmovie.core.data.RemoteDataSource
import com.belka.velka.testmovie.core.domain.Film

class ApiFilmsDataSource : RemoteDataSource {
    val api = API.getAPI()

    private var films = listOf<Film>()

    private fun loadFilms() {
        if (films.isEmpty()) {
            api.getMovies().execute().body()?.let {
                films = it
            }
        }
    }

    override suspend fun getAllFilms(): List<Film> {
        loadFilms()

        return films
    }
}