package com.kabe.nasaphotosapi.features.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.kabe.nasaphotosapi.ui.theme.NasaPhotosApiTheme
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph


@RootNavGraph(start = true)
@Destination
@Composable
fun HomeScreen() {
    HomeScreenView()
}

@Composable
fun HomeScreenView() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Text(text = "HomeScreen")
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    NasaPhotosApiTheme {
        HomeScreen()
    }
}