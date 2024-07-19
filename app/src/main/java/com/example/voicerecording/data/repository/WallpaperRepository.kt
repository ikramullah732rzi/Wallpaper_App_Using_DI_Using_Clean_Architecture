package com.example.voicerecording.data.repository

import android.annotation.SuppressLint
import com.example.voicerecording.data.WallpaperDto
import com.example.voicerecording.data.endpoints.Endpoints
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.awaitResponse
import javax.inject.Inject


class WallpaperRepository  @Inject constructor(val endpoints: Endpoints) :RepoInterface {

     @SuppressLint("SuspiciousIndentation")
     override suspend fun getAllWallpaper(authorization: String, search: String): Response<WallpaperDto> {
        val response = endpoints.getAllWallpapersPhoto(authorization,search)
       return response
    }

/*override suspend fun searchWallpaper(): WallpaperDto {
        TODO("Not yet implemented")
    }*/

}
