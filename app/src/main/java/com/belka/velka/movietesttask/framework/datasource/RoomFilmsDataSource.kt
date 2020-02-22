package com.belka.velka.movietesttask.framework.datasource

import android.content.Context
import com.belka.velka.movietesttask.framework.datasource.db.FilmDatabase
import com.belka.velka.movietesttask.framework.datasource.db.entities.FilmEntity
import com.belka.velka.testmovie.core.data.LocalDataSource
import com.belka.velka.testmovie.core.domain.Film

class RoomFilmsDataSource(context: Context) : LocalDataSource {
    private val filmsDao = FilmDatabase.getInstance(context).filmDAO()

    override suspend fun getAllFilms(): List<Film> {
        return filmsDao.getFilms().map { Film(it.id, it.poster, it.year) }
    }

    override suspend fun saveFilms(films: List<Film>) {
        filmsDao.insertFilms(films.map { FilmEntity(it.id, it.poster, it.year) })
    }

}