package com.kabe.nasaphotosapi.data.model.response

import com.google.gson.annotations.SerializedName
import com.kabe.nasaphotosapi.domain.NasaPhoto

data class NasaPhotosResponse (
    @SerializedName("Photos") val results: List<NasaPhoto>
)