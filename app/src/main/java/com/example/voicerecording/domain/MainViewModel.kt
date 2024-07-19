package com.example.voicerecording.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import com.example.voicerecording.data.Photo
import com.example.voicerecording.data.WallpaperDto
import com.example.voicerecording.data.repository.WallpaperRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.await
import retrofit2.awaitResponse
import javax.inject.Inject

const val authorization = "HTyuyElplvFnppnK9ZX2YADZiM1eKyuHMYzeRQ968djMaGHLvsvDomh6"
const val searchTerm = "fruits"


@HiltViewModel
class WallpaperViewModel @Inject constructor(private val wallpaperRepository: WallpaperRepository) :
    ViewModel() {
    private val _wallpaperlist = MutableStateFlow<WallpaperDto?>(null)
    val wallpaperlist = _wallpaperlist.asStateFlow()

    val isSuccess = MutableStateFlow<Boolean?>(null)


    private val _photolist = MutableStateFlow<List<Photo?>?>(null)
    val photolist = _photolist.asStateFlow()

    init {
        getWallpapers(search = searchTerm)
    }

     fun getWallpapers(search :String) {
        viewModelScope.launch {
            val response = wallpaperRepository.getAllWallpaper(authorization, search)
            _wallpaperlist.update {
                response.body()
            }
            isSuccess.update {
            response.isSuccessful
            }
            _photolist.update {
                response.body()?.photos
            }
        }
    }

    // custom
    fun getCustomWallpapers(search :String) {
        viewModelScope.launch {
            val response = wallpaperRepository.getAllWallpaper(authorization, search)
            _wallpaperlist.update {
                response.body()
            }
            _photolist.update {
                response.body()?.photos
            }
        }
    }

}