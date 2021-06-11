package com.saliktariq.archelon.datalayer.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.saliktariq.archelon.datalayer.entity.Location
@Dao
interface LocationAccessObject {

    //Query to insert Location information in Location entity
    @Insert
    fun insertLocationData(location: Location)

    //Query to retrieve Location object based on locationID
    @Query("Select * from Location WHERE locationID = :key LIMIT 1")
    fun getLocationDataWithID(key: Long): Location?

    //Query to retrieve *all* Location objects based on nestID
    @Query("Select * from Location WHERE nestID = :key ORDER BY locationID DESC")
    fun getLocationDataWithNestID(key: Long): List<Location>?
}