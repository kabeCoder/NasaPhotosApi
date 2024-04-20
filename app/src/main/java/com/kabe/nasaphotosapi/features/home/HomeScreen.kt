package com.kabe.nasaphotosapi.features.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.kabe.nasaphotosapi.domain.NasaPhoto
import com.kabe.nasaphotosapi.ui.theme.NasaPhotosApiTheme
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph


@RootNavGraph(start = true)
@Destination
@Composable
fun HomeScreen() {
    val viewModel: HomeScreenViewModel = hiltViewModel()
    val nasaPhotoLists by viewModel.nasaPhotos.collectAsState(initial = emptyList())

    LaunchedEffect(key1 = null) {
        viewModel.getNasaPhotos()
    }

    HomeScreenView(
        nasaPhotoLists = nasaPhotoLists
    )
}

@Composable
fun HomeScreenView(
    nasaPhotoLists: List<NasaPhoto>,
) {
   Column(modifier = Modifier.fillMaxWidth()) {
       nasaPhotoLists.forEach { nasaPhoto ->
           Text(text = nasaPhoto.camera.fullName)
       }
   }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    NasaPhotosApiTheme {
        HomeScreen()
    }
}