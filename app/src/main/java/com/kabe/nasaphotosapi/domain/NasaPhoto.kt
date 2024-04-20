package com.kabe.nasaphotosapi.domain

import androidx.room.Entity
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "Rover")
data class NasaPhoto (
    @SerializedName("id") var author: Int,
) : Serializable