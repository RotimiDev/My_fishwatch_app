package com.example.oceangrsmithassessment.data.remote

import com.example.oceangrsmithassessment.data.model.FishWatch
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {

    @GET("species")
    suspend fun getFishWatch(
    ): Response<List<FishWatch>>

    @GET("fishWatch/{name}")
    suspend fun getFishWatchInfo(
        @Path("name") name: String
    ): FishWatch
}