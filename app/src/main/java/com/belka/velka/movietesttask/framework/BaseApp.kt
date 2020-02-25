package com.belka.velka.movietesttask.framework

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import androidx.appcompat.app.AlertDialog
import com.belka.velka.movietesttask.R
import com.belka.velka.movietesttask.framework.datasource.ApiFilmsDataSource
import com.belka.velka.movietesttask.framework.datasource.RoomFilmsDataSource
import com.belka.velka.movietesttask.framework.datasource.db.Interactors
import com.belka.velka.testmovie.core.data.FilmRepository
import com.belka.velka.testmovie.core.interactors.GetAllFilms
import com.belka.velka.testmovie.core.interactors.GetLatestFilms
import java.lang.Exception
import java.net.InetAddress

class BaseApp : Application() {
    override fun onCreate() {
        super.onCreate()

        val filmRepository = FilmRepository(
            RoomFilmsDataSource(
                this
            ),
            if(isInternetAvailable()) ApiFilmsDataSource() else null
        )

        ViewModelFactory.inject(
            this, Interactors(
                GetAllFilms(filmRepository),
                GetLatestFilms(filmRepository)
            )
        )

    }

    fun isInternetAvailable(): Boolean {
        val cm = (getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager)
        return cm.activeNetworkInfo != null && cm.activeNetworkInfo.isConnected
    }


}