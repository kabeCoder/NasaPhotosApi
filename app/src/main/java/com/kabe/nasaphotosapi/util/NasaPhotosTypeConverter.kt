package com.kabe.nasaphotosapi.util

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.kabe.nasaphotosapi.domain.Camera
import com.kabe.nasaphotosapi.domain.NasaPhoto
import com.kabe.nasaphotosapi.domain.Rover

class NasaPhotosTypeConverter {
    // Nasa Photo
    @TypeConverter
    fun fromCamera(json: String): Camera {
        return Gson().fromJson(json, Camera::class.java)
    }

    @TypeConverter
    fun toJson(camera: Camera): String {
        return Gson().toJson(camera)
    }

    // Rover

    @TypeConverter
    fun fromRover(json: String): Rover {
        return Gson().fromJson(json, Rover::class.java)
    }

    @TypeConverter
    fun toJson(rover: Rover): String {
        return Gson().toJson(rover)
    }
}