package com.belka.velka.testmovie.core.interactors

import com.belka.velka.testmovie.core.data.FilmRepository

class GetAllFilms(private val filmsRepository: FilmRepository) {
    suspend operator fun invoke() = filmsRepository.getAllFilms()
}