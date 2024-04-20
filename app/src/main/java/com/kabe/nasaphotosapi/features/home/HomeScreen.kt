package com.kabe.nasaphotosapi.features.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.kabe.nasaphotosapi.R
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
    val lazyListState = rememberLazyListState()

    LazyColumn(
        state = lazyListState,
        contentPadding = PaddingValues(8.dp),
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(nasaPhotoLists.take(10)) { nasaPhoto ->
            Card(modifier = Modifier.fillMaxWidth()) {
                Row {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(nasaPhoto.imgUrl.replace("http://", "https://"))
                            .crossfade(true)
                            .build(),
                        placeholder = null,
                        contentDescription = "",
                        modifier = Modifier
                            .height(120.dp)
                            .width(120.dp)
                    )
                    Column {
                        Text(text = stringResource(id = R.string.label_rover_name) + nasaPhoto.rover.name)
                        Text(text = stringResource(id = R.string.label_camera_name) + nasaPhoto.camera.fullName)
                        Text(text = stringResource(id = R.string.label_date_on_earth) + nasaPhoto.earthDate)
                    }
                }

            }
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