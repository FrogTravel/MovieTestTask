package com.belka.velka.movietesttask.framework

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.belka.velka.movietesttask.framework.datasource.db.Interactors

open class BaseViewModel(application: Application, protected val interactors: Interactors) : AndroidViewModel(application){
    protected val application : BaseApp = getApplication()
}