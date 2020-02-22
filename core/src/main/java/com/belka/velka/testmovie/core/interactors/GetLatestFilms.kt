package com.belka.velka.testmovie.core.interactors

import com.belka.velka.testmovie.core.data.FilmRepository

class GetLatestFilms(private val filmRepository: FilmRepository){
    suspend operator fun invoke() = filmRepository.getFilmsForYear(2020)
}