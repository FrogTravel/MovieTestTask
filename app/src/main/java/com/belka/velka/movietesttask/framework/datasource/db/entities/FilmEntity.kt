package com.belka.velka.movietesttask.framework.datasource.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FilmEntity(@PrimaryKey val id: String,
                val poster: String,
                val year: Int)