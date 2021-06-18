package com.dicoding.proyekakhir.core

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.dicoding.proyekakhir.core.enums.StatusResponse.*
import com.dicoding.proyekakhir.core.model.response.APIResponse
import com.dicoding.proyekakhir.core.utils.AppExecutors
import com.dicoding.proyekakhir.core.utils.Resource

abstract class NetworkBoundResource<ResultType, RequestType>(private val appExecutors: AppExecutors) {

    private val result = MediatorLiveData<Resource<ResultType>>()

    init {
        result.apply {
            value = Resource.loading(null)

            val source = loadFromDB()

            addSource(source) { data ->
                removeSource(source)

                if (shouldFetch(data)) {
                    fetchFromNetwork(source)
                } else {
                    addSource(source) { newData ->
                        value = Resource.success(newData)
                    }
                }
            }
        }
    }

    protected abstract fun shouldFetch(data: ResultType?): Boolean

    protected abstract fun loadFromDB(): LiveData<ResultType>

    protected abstract fun createCall(): LiveData<APIResponse<RequestType>>

    protected abstract fun saveCallResult(data: RequestType)

    private fun fetchFromNetwork(databaseSource: LiveData<ResultType>) {

        val apiResponse = createCall()

        result.apply {
            addSource(databaseSource) { newData ->
                value = Resource.loading(newData)
            }

            addSource(apiResponse) { response ->
                removeSource(apiResponse)
                removeSource(databaseSource)

                appExecutors.apply {
                    when (response.status) {
                        SUCCESS -> diskIO().execute {
                            saveCallResult(response.body)

                            mainThread().execute {
                                addSource(loadFromDB()) { newData ->
                                    value = Resource.success(newData)
                                }
                            }
                        }
                        EMPTY -> mainThread().execute {
                            addSource(loadFromDB()) { newData ->
                                value = Resource.success(newData)
                            }
                        }
                        ERROR -> {
                            addSource(databaseSource) { newData ->
                                value = Resource.error(newData, response.message)
                            }
                        }
                    }
                }
            }
        }
    }

    fun asLiveData(): LiveData<Resource<ResultType>> = result

}