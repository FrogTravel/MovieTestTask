package com.belka.velka.movietesttask.presentation.mainscreen

import android.app.Application
import androidx.lifecycle.*
import com.belka.velka.movietesttask.framework.BaseViewModel
import com.belka.velka.movietesttask.framework.datasource.db.Interactors
import com.belka.velka.testmovie.core.domain.Film
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainViewModel(application: Application, interactors: Interactors) :
    BaseViewModel(application, interactors) {

    val films = MutableLiveData<List<Film>>()

    fun loadFilms(){
        GlobalScope.launch {
            films.postValue(interactors.getAllFilms())
        }
    }
    
    fun loadLatestFilms(){
        GlobalScope.launch {
            films.postValue(interactors.getLatestFilms())
        }
    }
}