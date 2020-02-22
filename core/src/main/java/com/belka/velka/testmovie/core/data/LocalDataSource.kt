package com.belka.velka.testmovie.core.data

import com.belka.velka.testmovie.core.domain.Film

interface LocalDataSource {
    suspend fun getAllFilms(): List<Film>

    suspend fun saveFilms(films: List<Film>)
}