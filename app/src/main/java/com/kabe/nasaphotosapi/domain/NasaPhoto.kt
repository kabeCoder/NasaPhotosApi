package com.kabe.nasaphotosapi.domain

import androidx.room.Entity
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "Rover")
data class NasaPhoto (
    @SerializedName("id") var id: Int,
    @SerializedName("img_src") var imgUrl: String,
    @SerializedName("camera") var camera: Camera,
    @SerializedName("earth_date") var earthDate: String,
    @SerializedName("rover") var rover: Rover,
) : Serializable