package com.belka.velka.movietesttask.presentation.mainscreen

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.DiffUtil
import com.belka.velka.movietesttask.R
import com.belka.velka.testmovie.core.domain.Film
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.card_film.view.*

class FilmAdapter(var films: List<Film>) : RecyclerView.Adapter<FilmAdapter.ViewHolder>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(viewGroup.context).inflate(R.layout.card_film, null))
    }

    override fun getItemCount() = films.size

    override fun onBindViewHolder(viewHolder: ViewHolder, id: Int) {
        val film = films[id]
        Glide.with(viewHolder.view.context)
            .load(film.poster)
            .error(R.drawable.ic_launcher_background)
            .into(viewHolder.view.imageView)
    }

    fun updateList(newFilms: List<Film>){
        val diffResult = DiffUtil.calculateDiff(DiffCallback(films, newFilms))
        films = newFilms
        diffResult.dispatchUpdatesTo(this)
    }

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view)


    inner class DiffCallback(val oldFilms: List<Film>, val newFilm: List<Film>) : DiffUtil.Callback(){
        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) = oldFilms[oldItemPosition].id == newFilm[newItemPosition].id

        override fun getOldListSize() = oldFilms.size

        override fun getNewListSize() = newFilm.size

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) = oldFilms[oldItemPosition].id == newFilm[newItemPosition].id
    }
}