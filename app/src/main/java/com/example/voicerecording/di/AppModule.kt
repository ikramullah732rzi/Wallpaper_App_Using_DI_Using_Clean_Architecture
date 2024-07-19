package com.example.voicerecording.di

import com.example.voicerecording.data.endpoints.Endpoints
import com.example.voicerecording.data.repository.RepoInterface
import com.example.voicerecording.data.repository.WallpaperRepository


import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {


   /* @Provides
    @Singleton
    fun provideNewtorkCall(): Retrofit {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.pexels.com/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit
    }*/


    @Provides
    @Singleton
    fun provideEndpoint(): Endpoints {

        val client = OkHttpClient.Builder().build()
        val endpoints = Retrofit.Builder()
            .baseUrl("https://api.pexels.com/v1/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Endpoints::class.java)

        return endpoints
    }

    @Provides
    @Singleton
    fun provideRepository( endpoints: Endpoints): WallpaperRepository =
        WallpaperRepository(endpoints)

}

