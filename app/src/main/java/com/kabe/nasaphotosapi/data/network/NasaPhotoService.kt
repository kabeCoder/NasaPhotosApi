package com.kabe.nasaphotosapi.data.network

import com.kabe.nasaphotosapi.data.model.response.NasaPhotosResponse
import com.kabe.nasaphotosapi.domain.NasaPhoto
import retrofit2.http.GET

interface NasaPhotoService {
    @GET("?sol=1000&api_key=htzDUej1O2myh7zWWTAAv05WD5Te5mJZyKNHIMVX")
    suspend fun getNasaPhotos() : NasaPhotosResponse
}

