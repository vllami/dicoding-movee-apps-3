package com.dicoding.proyekakhir.core.local

import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase
import com.dicoding.proyekakhir.core.Constant.DATABASE_NAME
import com.dicoding.proyekakhir.core.model.entity.MoviesEntity
import com.dicoding.proyekakhir.core.model.entity.TvShowsEntity
import androidx.room.Room.databaseBuilder as Room

@Database(entities = [MoviesEntity::class, TvShowsEntity::class], version = 3, exportSchema = false)
abstract class MoveeDatabase : RoomDatabase() {

    abstract fun moveeDAO(): MoveeDAO

    companion object {
        @Volatile
        private var database: MoveeDatabase? = null

        fun getDatabase(context: Context): MoveeDatabase {
            return database ?: synchronized(this) {
                Room(context.applicationContext, MoveeDatabase::class.java, DATABASE_NAME)
                    .fallbackToDestructiveMigration()
                    .build().apply {
                        database = this
                    }
            }
        }
    }

}
