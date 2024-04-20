package com.kabe.nasaphotosapi.data.network

import com.kabe.nasaphotosapi.data.model.response.NasaPhotosResponse
import retrofit2.http.GET

interface NasaPhotoService {
    @GET("photos/?sol=1000&api_key=htzDUej1O2myh7zWWTAAv05WD5Te5mJZyKNHIMVX")
    suspend fun getNasaPhotos() : NasaPhotosResponse
}

