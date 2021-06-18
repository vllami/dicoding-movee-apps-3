package com.dicoding.proyekakhir.core.utils

import androidx.test.espresso.idling.CountingIdlingResource
import com.dicoding.proyekakhir.core.Constant.RESOURCE_NAME

object EspressoIdlingResource {

    val espressoTestIdlingResource = CountingIdlingResource(RESOURCE_NAME)

    fun increment() = espressoTestIdlingResource.increment()

    fun decrement() = espressoTestIdlingResource.decrement()

}