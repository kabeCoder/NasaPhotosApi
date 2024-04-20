package com.kabe.nasaphotosapi.domain

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class RoverCamera (
    @SerializedName("id") val cameraId: Int,
    @SerializedName("name") val name: String,
    @SerializedName("rover_id") val roverId: Int,
    @SerializedName("full_name") val fullName: String,
)   : Serializable