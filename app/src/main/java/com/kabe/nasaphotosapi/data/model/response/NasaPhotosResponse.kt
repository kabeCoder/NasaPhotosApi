package com.kabe.nasaphotosapi.data.model.response

import com.google.gson.annotations.SerializedName
import com.kabe.nasaphotosapi.domain.NasaPhoto
import com.kabe.nasaphotosapi.domain.Rover
import com.kabe.nasaphotosapi.domain.Camera

data class NasaPhotosResponse (
    @SerializedName("photos") val results: List<NasaPhoto>,
    @SerializedName("camera") val camera: List<Camera>,
    @SerializedName("rover") val rover: List<Rover>
)