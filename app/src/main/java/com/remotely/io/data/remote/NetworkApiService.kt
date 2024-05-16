package com.remotely.io.data.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface NetworkApiService {
    @GET
    suspend fun getHomeData(@Url url: String): Response<String>
}