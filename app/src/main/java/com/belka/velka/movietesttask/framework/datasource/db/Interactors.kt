package com.belka.velka.movietesttask.framework.datasource.db

import com.belka.velka.testmovie.core.interactors.GetAllFilms
import com.belka.velka.testmovie.core.interactors.GetLatestFilms

data class Interactors(val getAllFilms: GetAllFilms,
                       val getLatestFilms: GetLatestFilms)