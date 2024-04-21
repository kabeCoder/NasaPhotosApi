package com.kabe.nasaphotosapi.domain

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "Rover")
data class NasaPhoto (
    @PrimaryKey(autoGenerate = true) var iD: Int,
    @SerializedName("id") var roverId: Int,
    @SerializedName("img_src") var imgUrl: String,
    @SerializedName("camera") var camera: Camera? = null,
    @SerializedName("earth_date") var earthDate: String,
    @SerializedName("rover") var rover: Rover? = null,
) : Serializable