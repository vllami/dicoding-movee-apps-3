package com.dicoding.proyekakhir.core.model.response

import com.dicoding.proyekakhir.core.enums.StatusResponse
import com.dicoding.proyekakhir.core.enums.StatusResponse.ERROR
import com.dicoding.proyekakhir.core.enums.StatusResponse.SUCCESS

class APIResponse<T>(val status: StatusResponse, val body: T, val message: String?) {

    companion object {
        fun <T> success(body: T): APIResponse<T> = APIResponse(SUCCESS, body, null)

        fun <T> error(message: String, body: T): APIResponse<T> = APIResponse(ERROR, body, message)
    }

}