package com.belka.velka.movietesttask.presentation.mainscreen

import android.content.Context
import android.content.res.Configuration
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.belka.velka.movietesttask.R
import com.belka.velka.movietesttask.framework.ViewModelFactory
import com.belka.velka.testmovie.core.domain.Film
import kotlinx.android.synthetic.main.activity_main.*

class MainView : AppCompatActivity() {
    private lateinit var layoutManager: GridLayoutManager
    private lateinit var adapter: FilmAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (!isInternetAvailable()) {
            showNoInternetDialog()
        }

        initializeViews()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        layoutManager.spanCount = resources.getInteger(R.integer.number_of_columns)
    }


    fun initializeViews() {
        val model: MainViewModel =
            ViewModelProvider(this, ViewModelFactory).get(MainViewModel::class.java)

        model.loadFilms()

        adapter = FilmAdapter(model.films.value ?: emptyList())
        layoutManager = GridLayoutManager(this, resources.getInteger(R.integer.number_of_columns))
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter

        model.films.observe(this,
            Observer<List<Film>> {
                adapter.updateList(it)
                recyclerView.scrollToPosition(0)
            })


        switchLatest.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) model.loadLatestFilms() else model.loadFilms()
        }
    }

    fun isInternetAvailable(): Boolean {
        val cm = (getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager)
        return cm.activeNetworkInfo != null && cm.activeNetworkInfo.isConnected
    }

    fun showNoInternetDialog() {
        AlertDialog.Builder(this)
            .setTitle(R.string.internet_connection_error_title)
            .setMessage(R.string.internet_connection_error_body)
            .setPositiveButton(
                android.R.string.ok
            ) { _, _ -> }.show()
    }
}
