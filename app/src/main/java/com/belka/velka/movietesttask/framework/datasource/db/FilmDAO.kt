package com.belka.velka.movietesttask.framework.datasource.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.belka.velka.movietesttask.framework.datasource.db.entities.FilmEntity

@Dao
interface FilmDAO {
    @Insert
    fun insertFilms(films: List<FilmEntity>)

    @Query("SELECT * FROM filmentity")
    fun getFilms() : List<FilmEntity>
}