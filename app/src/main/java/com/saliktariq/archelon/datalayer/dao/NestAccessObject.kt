package com.saliktariq.archelon.datalayer.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.saliktariq.archelon.datalayer.entity.Nest

@Dao
interface NestAccessObject {
    //Query to insert Nest object
    @Insert
    fun insertNestData(nest: Nest)

    //Query to retrieve Nest object using nestID
    @Query("Select * from Nest where nestID = :key LIMIT 1")
    fun getNestByNestID(key: Long): Nest?
}