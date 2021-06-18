package com.dicoding.proyekakhir.core.utils

import com.dicoding.proyekakhir.core.enums.Status
import com.dicoding.proyekakhir.core.enums.Status.*

data class Resource<T>(val status: Status, val data: T?, val message: String?) {

    companion object {
        fun <T> success(data: T?): Resource<T> = Resource(SUCCESS, data, null)

        fun <T> error(data: T?, message: String?): Resource<T> = Resource(ERROR, data, message)

        fun <T> loading(data: T?): Resource<T> = Resource(LOADING, data, null)
    }

}