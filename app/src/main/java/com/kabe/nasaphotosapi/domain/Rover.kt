package com.kabe.nasaphotosapi.domain

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Rover(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("landing_date") val landingDate: String,
    @SerializedName("launch_date") val launchDate: String,
    @SerializedName("status") val status: String,
    @SerializedName("max_date") val maxDate: String,
    @SerializedName("total_photos") val totalPhotos: Int,
) : Serializable
