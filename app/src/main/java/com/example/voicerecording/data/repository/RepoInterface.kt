package com.example.voicerecording.data.repository

import com.example.voicerecording.data.WallpaperDto
import retrofit2.Response



interface RepoInterface {
  suspend  fun getAllWallpaper(authorization: String, search: String) : Response<WallpaperDto>
  //suspend  fun searchWallpaper() :WallpaperDto
}