package com.kabe.nasaphotosapi.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.kabe.nasaphotosapi.data.database.dao.NasaPhotosDao
import com.kabe.nasaphotosapi.domain.NasaPhoto
import com.kabe.nasaphotosapi.util.NasaPhotosTypeConverter

@Database(
    entities = [NasaPhoto::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(NasaPhotosTypeConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun nasaPhotosDao(): NasaPhotosDao
}