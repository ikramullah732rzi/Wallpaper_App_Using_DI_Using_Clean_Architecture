package com.example.voicerecording.presentation.screens

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.voicerecording.data.Photo
import com.example.voicerecording.domain.WallpaperViewModel
import com.example.voicerecording.presentation.DetailScreen

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "StateFlowValueCalledInComposition")
@Composable
fun HomeScreen(navHostController: NavHostController, wallpaperViewModel: WallpaperViewModel) {

    val optionList = listOf(
        "nature",
        "people",
        "fruits",
        "animals",

    )

    Scaffold(

        topBar = {
            TopAppBar(title = {
                Text(text = "Wallpaper App")

            })
        },
    ) {
        //   val list = wallpaperViewModel.photolist.collectAsState()
        val list = wallpaperViewModel.photolist.collectAsState()
        val searchText = remember {
            mutableStateOf("nature")
        }
        val isvalueTrue = remember {
            mutableStateOf(list.value)
        }
        wallpaperViewModel.getCustomWallpapers(searchText.value)

        Column(modifier = Modifier
            .fillMaxSize()
            .padding(it)) {

            Row (modifier = Modifier.fillMaxWidth()){
                // Search bar with fixed height
                OutlinedTextField(
                    value = searchText.value,
                    onValueChange = { searchText.value = it },
                    placeholder = { Text(text = "Search.....") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp)

                )
            }
            //------------------
            Spacer(modifier = Modifier.height(20.dp))
            //----------------
            LazyRow (modifier = Modifier.fillMaxWidth()){
                // Search bar with fixed height
                items(4){
                    rowSample(it,optionList){option->
                        wallpaperViewModel.getCustomWallpapers(option)
                    }
                }
            }
            //------------------
            Spacer(modifier = Modifier.height(20.dp))
            //------------------------

                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        contentPadding = PaddingValues(10.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                            .background(MaterialTheme.colorScheme.background),

                        ) {
                        list.value?.let { photo ->
                            items(photo) {
                                if (it != null) {
                                    Sample(i = it,navHostController)
                                }
                            }
                        }

                    }

        }

    }

}

@Composable
fun rowSample(i: Int,optionList :List<String>, lembda : (String)->Unit) {
    ElevatedButton(onClick = {
        lembda.invoke(optionList.get(i))
    }) {
        Text(text = optionList.get(i))
    }
}


@Composable
fun Sample(i: Photo, navHostController: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(5.dp)
            .clip(RoundedCornerShape(8.dp)),
        contentAlignment = Alignment.Center
    ) {

            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(i.src.large2x)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clickable {
                        navHostController.navigate(DetailScreen(imageurl = i.src.large2x)) {
                            popUpTo(DetailScreen(imageurl = i.src.large2x))
                            launchSingleTop = true
                        }
                    },
                contentScale = ContentScale.Crop,
            )
        }

        Log.d("TAG", "sample: ${i.photographer_url}")


    }
