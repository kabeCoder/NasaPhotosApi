package com.kabe.nasaphotosapi.data.respository

import com.kabe.nasaphotosapi.data.base.BaseRepository
import com.kabe.nasaphotosapi.data.base.Resource
import com.kabe.nasaphotosapi.data.network.NasaPhotoService
import com.kabe.nasaphotosapi.domain.NasaPhoto
import javax.inject.Inject

class NasaPhotoRepository @Inject constructor(
    private val nasaPhotoService : NasaPhotoService,
) : BaseRepository() {

    suspend fun getNasaPhotos() : Resource<List<NasaPhoto>> = serviceCall {
        val result =  nasaPhotoService.getNasaPhotos()

        result.results
    }
}