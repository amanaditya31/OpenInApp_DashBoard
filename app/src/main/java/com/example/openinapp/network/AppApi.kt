package com.example.openinapp.network


import com.example.openinapp.model.DataResponse
import dagger.Provides
import retrofit2.http.GET
import javax.inject.Singleton

@Singleton
interface AppApi {
    @GET
    suspend fun getDataResponse() : DataResponse
}
