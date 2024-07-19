package com.example.voicerecording.data.endpoints

import com.example.voicerecording.data.WallpaperDto
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface Endpoints {

    @GET("search")
    suspend fun getAllWallpapersPhoto(
        @Header("Authorization") authorization: String,  // Use lowercase 'authorization'
        @Query("query") search: String = "nature"
    ): Response<WallpaperDto>


    @GET("/search")
    suspend fun getwithActivity(
        @Header("Authorization") authorization: String,  // Use lowercase 'authorization'
        @Query("query") search: String
    ): Call<WallpaperDto>

}
