package com.dicoding.proyekakhir.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.dicoding.proyekakhir.data.remote.repository.Repository
import com.dicoding.proyekakhir.ui.details.DetailsViewModel
import com.dicoding.proyekakhir.ui.home.HomeViewModel
import com.dicoding.proyekakhir.ui.home.fab_watchlist.WatchlistViewModel
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory as ViewModelInstanceFactory
import com.dicoding.proyekakhir.di.Injection.provideRepository as InjectionProvideRepository

class ViewModelFactory private constructor(private val repository: Repository) : ViewModelInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        modelClass.apply {
            return when {
                isAssignableFrom(HomeViewModel::class.java) -> HomeViewModel(repository) as T
                isAssignableFrom(DetailsViewModel::class.java) -> DetailsViewModel(repository) as T
                isAssignableFrom(WatchlistViewModel::class.java) -> WatchlistViewModel(repository) as T
                else -> throw Throwable("Unknown ViewModel $name")
            }
        }
    }

    companion object {
        @Volatile
        private var viewModelFactory: ViewModelFactory? = null

        fun getViewModelFactory(context: Context): ViewModelFactory {
            return viewModelFactory ?: synchronized(this) {
                viewModelFactory ?: ViewModelFactory(InjectionProvideRepository(context)).apply {
                    viewModelFactory = this
                }
            }
        }
    }

}