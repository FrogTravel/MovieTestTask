package com.belka.velka.movietesttask.presentation.mainscreen

import android.app.Application
import androidx.lifecycle.*
import com.belka.velka.movietesttask.framework.BaseViewModel
import com.belka.velka.movietesttask.framework.datasource.db.Interactors
import com.belka.velka.testmovie.core.domain.Film
import kotlinx.coroutines.*

class MainViewModel(application: Application, interactors: Interactors) :
    BaseViewModel(application, interactors) {

    val films = MutableLiveData<List<Film>>()

    fun loadFilms() {
        viewModelScope.launch {
            withContext(Dispatchers.Default){
                films.postValue(interactors.getAllFilms())
            }
        }
    }


    fun loadLatestFilms() {
        viewModelScope.launch {
            withContext(Dispatchers.Default){
                films.postValue(interactors.getLatestFilms())
            }
        }
    }
}