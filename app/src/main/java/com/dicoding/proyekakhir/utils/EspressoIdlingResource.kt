package com.dicoding.proyekakhir.utils

import androidx.test.espresso.idling.CountingIdlingResource

object  EspressoIdlingResource {

    private val espressoTestIdlingResource = CountingIdlingResource("GLOBAL")

    fun increment() = espressoTestIdlingResource.increment()

    fun decrement() = espressoTestIdlingResource.decrement()

}