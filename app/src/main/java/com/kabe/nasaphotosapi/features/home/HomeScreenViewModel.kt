package com.kabe.nasaphotosapi.features.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kabe.nasaphotosapi.data.base.Status
import com.kabe.nasaphotosapi.data.respository.NasaPhotoRepository
import com.kabe.nasaphotosapi.domain.NasaPhoto
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val nasaPhotoRepository: NasaPhotoRepository,
): ViewModel() {

    private val _errorMessage = MutableSharedFlow<String?>()
    val errorMessage: SharedFlow<String?> = _errorMessage.asSharedFlow()

    private val _nasaPhotos = MutableSharedFlow<List<NasaPhoto>>()
    val nasaPhotos: SharedFlow<List<NasaPhoto>> = _nasaPhotos.asSharedFlow()

    private val _loadingStateNasaPhotos = MutableStateFlow(false)
    val loadingStateNasaPhotos: StateFlow<Boolean> = _loadingStateNasaPhotos


    suspend fun getNasaPhotos() {
        _loadingStateNasaPhotos.emit(true)
        viewModelScope.launch {
            val nasaPhotoResults = nasaPhotoRepository.getNasaPhotos()
            when (nasaPhotoResults.status) {
                Status.SUCCESS -> nasaPhotoResults.data?.let { nasaPhotos ->
                    _loadingStateNasaPhotos.emit(false)
                    _nasaPhotos.emit(nasaPhotos.toMutableList())
                    Log.d("HomeScreenViewModel", "Success")
                }

                Status.ERROR -> {
                    _loadingStateNasaPhotos.emit(false)
                    nasaPhotoResults.message?.let { _errorMessage.emit(it) }
                    Log.d("HomeScreenViewModel", "Failed")
                }
            }
        }
    }

}