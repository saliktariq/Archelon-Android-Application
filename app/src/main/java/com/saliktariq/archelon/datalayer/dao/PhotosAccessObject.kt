package com.saliktariq.archelon.datalayer.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.saliktariq.archelon.datalayer.entity.Photos

@Dao
interface PhotosAccessObject {

    //Query to insert Photos object
    @Insert
    fun insertPhotoData(photoData: Photos)

    //Query to retrieve Photos object
    @Query("Select * from Photos WHERE photoID = :key LIMIT 1")
    fun getAllPhotoData(key: Long): Photos?

    //Select all photos related to a particular nestID
    @Query("SELECT photo from Photos WHERE nestID = :key ORDER BY photoID")
    fun getNestPhotos(key: Long): List<ByteArray>?

    //As API doesn't support actual photos, query to retrieve photos file names
    //for a particular nestID
    @Query("SELECT photoFileName from Photos WHERE nestID = :key ORDER BY photoID")
    fun getNestPhotoFileNames(key: Long): List<String>?
}