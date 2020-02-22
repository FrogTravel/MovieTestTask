package com.belka.velka.testmovie.core.data

import com.belka.velka.testmovie.core.domain.Film

class FilmRepository(
    private val localDataSource: LocalDataSource,
    private val webDataSource: RemoteDataSource
) {
    var films = listOf<Film>()

    private suspend fun loadFilms() {
        if (films.isEmpty()) {//it might be already cached
            println("FILMS IS EMPTY IN VARIABLE")
            films = localDataSource.getAllFilms()
            if (films.isEmpty()) {//if there is nothing in DB then load from server
                println("FILMS IS EMPTY IN DB")
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