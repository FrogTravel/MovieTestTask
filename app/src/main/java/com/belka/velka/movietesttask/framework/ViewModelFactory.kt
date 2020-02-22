package com.belka.velka.movietesttask.framework

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.belka.velka.movietesttask.framework.datasource.db.Interactors
import java.lang.IllegalStateException

object ViewModelFactory : ViewModelProvider.Factory {

    lateinit var application: Application
    lateinit var dependencies: Interactors

    fun inject(application: Application, dependencies: Interactors){
        ViewModelFactory.dependencies = dependencies
        ViewModelFactory.application = application
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (BaseViewModel::class.java.isAssignableFrom(modelClass)) {
            return modelClass.getConstructor(Application::class.java, Interactors::class.java)
                .newInstance(
                    application,
                    dependencies
                )
        }else{
            throw IllegalStateException("ViewModel must extend BaseViewModel class")
        }
    }

}