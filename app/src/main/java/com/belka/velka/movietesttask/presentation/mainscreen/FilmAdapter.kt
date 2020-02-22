package com.belka.velka.movietesttask.presentation.mainscreen

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import com.belka.velka.movietesttask.R
import com.belka.velka.testmovie.core.domain.Film
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.card_film.view.*

class FilmAdapter(val films: LiveData<List<Film>>?) : RecyclerView.Adapter<FilmAdapter.ViewHolder>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(viewGroup.context).inflate(R.layout.card_film, null))
    }

    override fun getItemCount() = films?.value?.size ?: 0

    override fun onBindViewHolder(viewHolder: ViewHolder, id: Int) {
        films?.value?.let {
            val film = it[id]
            Glide.with(viewHolder.view.context)
                .load(film.poster)
                .error(R.drawable.ic_launcher_background)
                .into(viewHolder.view.imageView)
        }

    }

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view)
}