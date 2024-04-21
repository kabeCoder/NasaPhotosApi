package com.kabe.nasaphotosapi.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kabe.nasaphotosapi.domain.NasaPhoto

@Dao
abstract class NasaPhotosDao {
    @Query("SELECT * FROM Rover")
    abstract suspend fun getAllCurrentNasaPhotos(): List<NasaPhoto>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertCurrentNasaPhotos(randomPerson: List<NasaPhoto>)

}