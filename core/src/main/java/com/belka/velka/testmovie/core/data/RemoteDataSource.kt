package com.belka.velka.testmovie.core.data

import com.belka.velka.testmovie.core.domain.Film

interface RemoteDataSource {
    suspend fun getAllFilms(): List<Film>
}