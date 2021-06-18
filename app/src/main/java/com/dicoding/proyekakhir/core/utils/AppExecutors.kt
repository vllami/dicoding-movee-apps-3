package com.dicoding.proyekakhir.core.utils

import android.os.Handler
import android.os.Looper.getMainLooper
import androidx.annotation.VisibleForTesting
import java.util.concurrent.Executor
import java.util.concurrent.Executors.newSingleThreadExecutor

class AppExecutors @VisibleForTesting constructor(private val diskIO: Executor, private val mainThread: Executor) {

    constructor() : this(
        newSingleThreadExecutor(),
        MainThreadExecutor()
    )

    fun diskIO(): Executor = diskIO

    fun mainThread(): Executor = mainThread

    private class MainThreadExecutor : Executor {
        private val mainThreadHandler = Handler(getMainLooper())

        override fun execute(command: Runnable) {
            mainThreadHandler.post(command)
        }
    }

}