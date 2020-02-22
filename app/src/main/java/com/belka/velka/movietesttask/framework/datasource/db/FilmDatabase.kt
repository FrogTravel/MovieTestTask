package com.belka.velka.movietesttask.framework.datasource.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.belka.velka.movietesttask.framework.datasource.db.entities.FilmEntity

@Database(entities = [FilmEntity::class], version = 1)
abstract class FilmDatabase : RoomDatabase() {
    companion object {
        private const val DATABASE_NAME = "film"

        private var instance: FilmDatabase? = null

        private fun create(context: Context): FilmDatabase = Room.databaseBuilder(context, FilmDatabase::class.java, DATABASE_NAME).build()

        fun getInstance(context: Context): FilmDatabase = (instance ?: create(context)).also { instance = it }
    }

    abstract fun filmDAO(): FilmDAO
}