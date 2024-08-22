package com.example.voicerecording.presentation.screens

import android.Manifest
import android.annotation.SuppressLint
import android.app.DownloadManager
import android.app.WallpaperManager
import android.content.Context
import android.content.Context.DOWNLOAD_SERVICE
import android.graphics.Bitmap
import android.net.Uri
import android.os.Environment
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.voicerecording.presentation.DetailScreen
import com.example.voicerecording.presentation.HOME
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState


@OptIn(ExperimentalPermissionsApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DetailComposableScreen(navHostController: NavHostController, detailScreen: DetailScreen) {

    val coroutineScope = rememberCoroutineScope()
    val showRationalDialog = remember { mutableStateOf(false) }
    val context = LocalContext.current
    val permission = rememberPermissionState(
        permission = Manifest.permission.WRITE_EXTERNAL_STORAGE
    )

    Scaffold {
        ConstraintLayout(modifier = Modifier.padding(it)) {
            val (favoriteButton, downloadbutton, backbutton, backgroundimage, gridient, row, shareButton) = createRefs()

            //-----------------------------------
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(detailScreen.imageurl)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .constrainAs(backgroundimage) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom)
                        top.linkTo(parent.top)
                    },
                contentScale = ContentScale.Crop,
            )
            //------------------------------------------

            Icon(
                imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
                contentDescription = "backbutton",
                modifier = Modifier
                    .padding(start = 20.dp, top = 10.dp)
                    .constrainAs(backbutton) {
                        start.linkTo(parent.start)
                        top.linkTo(parent.top)
                    }
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(Color.White)
                    .padding(5.dp)
                    .clickable {
                        navHostController.navigate(HOME) {
                            popUpTo(HOME)
                            launchSingleTop = true
                        }
                    }

            )
            //


            // Gradient for half screen
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .constrainAs(gridient) {
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(backgroundimage.bottom)  // Start gradient from bottom of image
                    }
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent, // Transparent at top
                                Color.White // Black with 50% opacity at bottom
                            )
                        )
                    )
            )


            //-------------------------------------------
            Button(
                onClick = {
                    saveImagetoGallery(detailScreen.imageurl, context)
                },
                modifier = Modifier
                    .padding(bottom = 20.dp)
                    .constrainAs(downloadbutton) {
                        bottom.linkTo(parent.bottom)
                        end.linkTo(parent.end)
                    }
            ) {
                Text(text = "Download")
            }

            //----------------
            Icon(
                imageVector = Icons.Outlined.Share,
                contentDescription = "share button",
                modifier = Modifier
                    .padding(bottom = 20.dp, end = 10.dp)
                    .constrainAs(shareButton) {
                        end.linkTo(downloadbutton.start)
                        top.linkTo(downloadbutton.top)
                        bottom.linkTo(downloadbutton.bottom)
                    }
                    .size(50.dp)
                    .clip(CircleShape)
                    .background(Color.White)
                    .padding(10.dp)


            )
            //--------------------------
            Icon(
                imageVector = Icons.Outlined.FavoriteBorder,
                contentDescription = "favorite",
                modifier = Modifier
                    .padding(bottom = 20.dp, end = 10.dp)
                    .constrainAs(favoriteButton) {
                        start.linkTo(parent.start)
                        top.linkTo(shareButton.top)
                        bottom.linkTo(shareButton.bottom)
                    }
                    .size(50.dp)
                    .clip(CircleShape)
                    .background(Color.White)
                    .padding(10.dp)


            )


            createHorizontalChain(
                favoriteButton,
                shareButton,
                downloadbutton,
                chainStyle = ChainStyle.Packed
            )
            //--------------------------------

            //  -------------------------------------------

        }
    }


}


fun saveImagetoGallery(imageurl: String, context: Context) {
    val downloadManager: DownloadManager =
        context.getSystemService(DOWNLOAD_SERVICE) as DownloadManager
    val uri = Uri.parse(imageurl)
    val request = DownloadManager.Request(uri)
        .setTitle("Wallpaper${imageurl}")
        .setAllowedOverRoaming(false)
        .setMimeType("image/jpeg")
        .setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE)
        .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
        .setDestinationInExternalPublicDir(
            Environment.DIRECTORY_PICTURES,
            "Wallpaper/wall${System.currentTimeMillis()}" + ".jpg"
        )
    downloadManager.enqueue(request)
    Toast.makeText(context, "Successfully Downloaded", Toast.LENGTH_SHORT).show()

}

@Composable
fun GradientBackground(modifier: Modifier) {
    Box(
        modifier = modifier
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFFFF0000), // Light gray
                        Color(0xFF4CAF50) // Dark gray
                    )
                )
            )
    )
}