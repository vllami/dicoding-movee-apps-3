package com.dicoding.proyekakhir.core.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.dicoding.proyekakhir.core.di.Injection.provideRepository
import com.dicoding.proyekakhir.core.repository.MoveeRepository
import com.dicoding.proyekakhir.ui.details.DetailsViewModel
import com.dicoding.proyekakhir.ui.main.MainViewModel
import com.dicoding.proyekakhir.ui.main.watchlist.WatchlistViewModel
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory as ViewModelInstanceFactory

class ViewModelFactory private constructor(private val moveeRepository: MoveeRepository) : ViewModelInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        modelClass.apply {
            return when {
                isAssignableFrom(MainViewModel::class.java) -> MainViewModel(moveeRepository) as T
                isAssignableFrom(DetailsViewModel::class.java) -> DetailsViewModel(moveeRepository) as T
                isAssignableFrom(WatchlistViewModel::class.java) -> WatchlistViewModel(moveeRepository) as T
                else -> throw Throwable("Unknown ViewModel $name")
            }
        }
    }

    companion object {
        @Volatile
        private var viewModelFactory: ViewModelFactory? = null

        fun getViewModelFactory(context: Context): ViewModelFactory {
            return viewModelFactory ?: synchronized(this) {
                viewModelFactory ?: ViewModelFactory(provideRepository(context)).apply {
                    viewModelFactory = this
                }
            }
        }
    }

}