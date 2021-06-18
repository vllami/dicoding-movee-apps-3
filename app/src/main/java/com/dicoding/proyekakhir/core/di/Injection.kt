package com.dicoding.proyekakhir.core.di

import android.content.Context
import com.dicoding.proyekakhir.core.local.MoveeDatabase.Companion.getDatabase
import com.dicoding.proyekakhir.core.repository.MoveeRepository
import com.dicoding.proyekakhir.core.repository.MoveeRepository.Companion.getRepository
import com.dicoding.proyekakhir.core.source.LocalDataSource.Companion.getLocalDataSource
import com.dicoding.proyekakhir.core.source.RemoteDataSource.Companion.getRemoteDataSource
import com.dicoding.proyekakhir.core.utils.AppExecutors

object Injection {

    fun provideRepository(context: Context): MoveeRepository {
        return getRepository(
            getRemoteDataSource(),
            getLocalDataSource(getDatabase(context).moveeDAO()),
            AppExecutors()
        )
    }

}