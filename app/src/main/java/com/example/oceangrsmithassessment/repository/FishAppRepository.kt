package com.example.oceangrsmithassessment.repository

import com.example.oceangrsmithassessment.data.remote.Api
import com.example.oceangrsmithassessment.data.model.FishWatch
import retrofit2.Response
import javax.inject.Inject

class FishAppRepository @Inject constructor(
    private val api: Api
) {

    // Get responses for calls made by Api
    suspend fun getFishWatch(): Response<Array<FishWatch>> {
        return api.getFishWatch()
    }
}