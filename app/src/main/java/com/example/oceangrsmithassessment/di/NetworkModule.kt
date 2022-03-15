package com.example.oceangrsmithassessment.di

import com.example.oceangrsmithassessment.data.remote.Api
import com.example.oceangrsmithassessment.repository.FishAppRepository
import com.example.oceangrsmithassessment.util.Constant.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideFishAppRepository(
        api: Api
    ) = FishAppRepository(api)

    @Singleton
    @Provides
    fun provideApi(): Api {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(Api::class.java)
    }
}