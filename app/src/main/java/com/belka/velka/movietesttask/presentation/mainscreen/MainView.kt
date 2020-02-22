package com.belka.velka.movietesttask.presentation.mainscreen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.belka.velka.movietesttask.R
import com.belka.velka.movietesttask.framework.ViewModelFactory
import com.belka.velka.testmovie.core.domain.Film
import kotlinx.android.synthetic.main.activity_main.*

class MainView : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val model: MainViewModel = ViewModelProvider(this, ViewModelFactory).get(MainViewModel::class.java)

        model.loadFilms()

        val adapter = FilmAdapter(model.films)
        recyclerView.layoutManager = GridLayoutManager(this, 3)
        recyclerView.adapter = adapter

        model.films.observe(this,
            Observer<List<Film>> {
                adapter.notifyDataSetChanged()
            })


        switchLatest.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked) model.loadLatestFilms() else model.loadFilms()
        }
    }
}
