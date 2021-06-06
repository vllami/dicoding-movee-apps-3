package com.dicoding.proyekakhir.di

import android.content.Context
import com.dicoding.proyekakhir.data.remote.repository.Repository
import com.dicoding.proyekakhir.data.remote.repository.Repository.Companion.getRepository
import com.dicoding.proyekakhir.data.remote.source.LocalDataSource.Companion.getLocalDataSource
import com.dicoding.proyekakhir.data.remote.source.RemoteDataSource.Companion.getRemoteDataSource
import com.dicoding.proyekakhir.data.room.MoveeDatabase
import com.dicoding.proyekakhir.utils.AppExecutors

object Injection {

    fun provideRepository(context: Context): Repository {
        val coreDatabase = MoveeDatabase.getInstance(context)
        val remoteDataSource = getRemoteDataSource()
        val localDataSource = getLocalDataSource(coreDatabase.moveeDAO())
        val appExecutors = AppExecutors()

        return getRepository(remoteDataSource, localDataSource, appExecutors)
    }

}