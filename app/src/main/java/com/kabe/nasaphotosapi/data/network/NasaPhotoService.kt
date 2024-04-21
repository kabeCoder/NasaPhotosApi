package com.kabe.nasaphotosapi.data.network

import com.kabe.nasaphotosapi.BuildConfig
import com.kabe.nasaphotosapi.data.model.response.NasaPhotosResponse
import retrofit2.http.GET

interface NasaPhotoService {
    @GET("photos/?sol=1000&api_key=${BuildConfig.API_KEY}")
    suspend fun getNasaPhotos() : NasaPhotosResponse
}

