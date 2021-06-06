package com.dicoding.proyekakhir.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dicoding.proyekakhir.data.entity.MoviesEntity
import com.dicoding.proyekakhir.data.entity.TvShowsEntity

@Database(entities = [MoviesEntity::class, TvShowsEntity::class], version = 3, exportSchema = false)
abstract class MoveeDatabase : RoomDatabase() {

    abstract fun moveeDAO(): MoveeDAO

    companion object {

        @Volatile
        private var INSTANCE: MoveeDatabase? = null

        fun getInstance(context: Context): MoveeDatabase =
            INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    MoveeDatabase::class.java,
                    "movee.db"
                ).fallbackToDestructiveMigration().build().apply { INSTANCE = this }
            }
    }

}
