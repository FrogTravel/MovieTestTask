package com.belka.velka.testmovie.core.data

import com.belka.velka.testmovie.core.domain.Film

class FilmRepository(
    private val localDataSource: LocalDataSource,
    private val webDataSource: RemoteDataSource? = null
) {
    private var films = listOf<Film>()

    private suspend fun loadFilms() {
        if (films.isEmpty()) {//it might be already cached
            films = localDataSource.getAllFilms()
            if (films.isEmpty() && webDataSource != null) {//if there is nothing in DB then load from server, if server is available
                films = webDataSource.getAllFilms()
                localDataSource.saveFilms(films)
            }
        }
    }

    suspend fun getAllFilms(): List<Film> {
        loadFilms()

        return films
    }

    suspend fun getFilmsForYear(year: Int): List<Film> {
        loadFilms()

        return films.filter { it.year == year }
    }
}