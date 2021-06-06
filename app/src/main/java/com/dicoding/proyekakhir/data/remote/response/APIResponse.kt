package com.dicoding.proyekakhir.data.remote.response

class APIResponse<T>(val status: StatusResponse, val body: T, val message: String?) {

    companion object {
        fun <T> success(body: T): APIResponse<T> = APIResponse(StatusResponse.SUCCESS, body, null)

        fun <T> empty(message: String, body: T): APIResponse<T> = APIResponse(StatusResponse.EMPTY, body, message)

        fun <T> error(message: String, body: T): APIResponse<T> = APIResponse(StatusResponse.ERROR, body, message)
    }

}