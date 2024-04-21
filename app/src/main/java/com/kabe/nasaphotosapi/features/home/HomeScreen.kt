package com.kabe.nasaphotosapi.features.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.kabe.nasaphotosapi.R
import com.kabe.nasaphotosapi.domain.NasaPhoto
import com.kabe.nasaphotosapi.features.home.views.PullToRefreshLazyColumn
import com.kabe.nasaphotosapi.ui.theme.NasaPhotosApiTheme
import com.kabe.nasaphotosapi.ui.theme.spacing
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph


@RootNavGraph(start = true)
@Destination
@Composable
fun HomeScreen() {
    val viewModel: HomeScreenViewModel = hiltViewModel()
    val nasaPhotoLists by viewModel.nasaPhotos.collectAsState(initial = emptyList())
    val nasaPhotosLoadingState by viewModel.loadingStateNasaPhotos.collectAsState(initial = false)


    if (nasaPhotosLoadingState) {
        CircularProgressIndicator(
            modifier = Modifier
                .fillMaxSize()
                .padding(MaterialTheme.spacing.extraLarge + MaterialTheme.spacing.extraLarge + MaterialTheme.spacing.extraLarge)
        )
    } else {
        HomeScreenView(
            nasaPhotoLists = nasaPhotoLists,
            viewModel = viewModel,
        )
    }
}

@Composable
fun HomeScreenView(
    nasaPhotoLists: List<NasaPhoto>,
    viewModel: HomeScreenViewModel,
) {

    Box(modifier = Modifier.fillMaxSize()) {

        PullToRefreshLazyColumn(
            items = nasaPhotoLists,
            content = { nasaPhoto ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(MaterialTheme.spacing.medium)
                        .clickable {
                            //navigator?.navigate(DetailScreenDestination(randomPerson))
                        }
                ) {
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

                            Text(text = stringResource(id = R.string.label_rover_name) + (nasaPhoto.rover?.name
                                ?: ""))
                            Text(text = stringResource(id = R.string.label_camera_name) + (nasaPhoto.camera?.fullName
                                ?: ""))
                            Text(text = stringResource(id = R.string.label_date_on_earth) + nasaPhoto.earthDate)
                        }
                    }
                }
            },
            isRefreshing = viewModel.loadingStateNasaPhotos.collectAsState().value,
            onRefresh = {
                viewModel.getNasaPhotos()
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    NasaPhotosApiTheme {
        HomeScreen()
    }
}