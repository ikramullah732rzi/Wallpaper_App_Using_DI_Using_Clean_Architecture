package com.example.voicerecording.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.voicerecording.domain.WallpaperViewModel
import com.example.voicerecording.presentation.screens.HomeScreen
import com.example.voicerecording.presentation.screens.SplashScreen
import com.example.voicerecording.presentation.screens.DetailComposableScreen
import com.example.voicerecording.ui.theme.VoiceRecordingTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.serialization.Serializable

@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "StateFlowValueCalledInComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            VoiceRecordingTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    MainApp()
                }
            }
        }
    }
}


@Composable
fun MainApp() {
    val navHostController = rememberNavController()

    NavHost(
        navController = navHostController,
        startDestination = SPLISHSCREEN
    ) {
        composable<HOME> {
            HomeScreen(navHostController, wallpaperViewModel = hiltViewModel<WallpaperViewModel>())
        }
        composable<DetailScreen>{
            val detailScreen = it.toRoute<DetailScreen>()
            DetailComposableScreen(navHostController,detailScreen)
        }
        composable<SPLISHSCREEN>{
            SplashScreen(  navHostController)
        }

    }
}






@Serializable
object HOME

@Serializable
object SPLISHSCREEN


@Serializable
data class DetailScreen(
    val imageurl: String,
)




