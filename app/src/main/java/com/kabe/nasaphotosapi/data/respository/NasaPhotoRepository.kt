package com.kabe.nasaphotosapi.data.respository

import com.kabe.nasaphotosapi.data.base.BaseRepository
import com.kabe.nasaphotosapi.data.base.Resource
import com.kabe.nasaphotosapi.data.database.AppDatabase
import com.kabe.nasaphotosapi.data.network.NasaPhotoService
import com.kabe.nasaphotosapi.domain.NasaPhoto
import javax.inject.Inject

class NasaPhotoRepository @Inject constructor(
    private val nasaPhotoService : NasaPhotoService,
    private val appDatabase: AppDatabase,
) : BaseRepository() {

    suspend fun getNasaPhotos() : Resource<List<NasaPhoto>> = serviceCall {
        val result =  nasaPhotoService.getNasaPhotos()

        appDatabase.nasaPhotosDao().insertCurrentNasaPhotos(result.results)

        result.results
    }

    suspend fun getCachedNasaPhotos(): List<NasaPhoto> {
        return appDatabase.nasaPhotosDao().getAllCurrentNasaPhotos()
    }
}