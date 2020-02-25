package com.belka.velka.movietesttask.framework

import android.app.Application
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

//        if(!isInternetAvailable()){
//            AlertDialog.Builder(applicationContext)
//                .setTitle(R.string.internet_connection_error_title)
//                .setMessage(R.string.internet_connection_error_body)
//                .setPositiveButton(android.R.string.ok
//                ) { _, _ -> onTerminate()}.show()
//        }else {
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
//        }
    }

    fun isInternetAvailable(): Boolean{
//        return (getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).activeNetworkInfo.isConnected
        return try{
            val ipAddr = InetAddress.getAllByName("google.com")
            !ipAddr.equals("")
        }catch (e: Exception){
            false
        }
    }
}