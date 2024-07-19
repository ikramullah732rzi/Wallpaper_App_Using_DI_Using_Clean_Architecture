package com.example.voicerecording.presentation.screens

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward

import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.voicerecording.R
import com.example.voicerecording.domain.WallpaperViewModel
import com.example.voicerecording.presentation.HOME

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SplashScreen(navHostController: NavHostController) {



    Scaffold{
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            Column(modifier = Modifier.fillMaxSize()) {

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.splishbackground_image), // Replace with your image resource
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxSize()
                            .align(Alignment.TopCenter)
                            .clip(RoundedCornerShape(bottomEnd = 10.dp, bottomStart = 10.dp)),
                        contentScale = ContentScale.Crop
                    )

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.BottomCenter)
                            .background(
                                brush = Brush.linearGradient(
                                    listOf(
                                        Color.Transparent,
                                        Color.Magenta
                                    )
                                )
                            )
                    )

                }


            }

            Text(
                text = "Explore AI Wallpapers",
                style = TextStyle(
                    fontSize = 30.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.White,
                ),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .align(Alignment.Center)

            )

            Text(
                text = "A Glimpse in to the Fature",
                style = TextStyle(
                    fontSize = 20.sp,
                    color = Color.White,
                ),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(top = 100.dp)
            )


            ElevatedButton(
                onClick = {
                    // Navigate to the main screen
                      navHostController.navigate(HOME){
                          popUpTo(HOME)
                          launchSingleTop = true
                      }
                },
                modifier = Modifier
                    .padding(start = 20.dp, end = 20.dp)
                    .align(Alignment.Center)
                    .padding(top = 220.dp)
            ) {
                Text(text = "Dive Deeper")
                Spacer(modifier = Modifier.width(50.dp))
                Icon(imageVector = Icons.Filled.ArrowForward,
                    contentDescription = "farward icons",
                    tint = Color.Black
                )
            }


        }
    }
}




