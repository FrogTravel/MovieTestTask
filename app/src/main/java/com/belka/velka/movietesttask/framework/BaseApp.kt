package com.belka.velka.movietesttask.framework

import android.app.Application
import com.belka.velka.movietesttask.framework.datasource.ApiFilmsDataSource
import com.belka.velka.movietesttask.framework.datasource.RoomFilmsDataSource
import com.belka.velka.movietesttask.framework.datasource.db.Interactors
import com.belka.velka.testmovie.core.data.FilmRepository
import com.belka.velka.testmovie.core.interactors.GetAllFilms
import com.belka.velka.testmovie.core.interactors.GetLatestFilms

class BaseApp : Application() {
    override fun onCreate() {
        super.onCreate()

        val filmRepository = FilmRepository(
            RoomFilmsDataSource(
                this
            ),
            ApiFilmsDataSource()
        )

        ViewModelFactory.inject(
            this, Interactors(
                GetAllFilms(filmRepository),
                GetLatestFilms(filmRepository)
            )
        )
    }
}