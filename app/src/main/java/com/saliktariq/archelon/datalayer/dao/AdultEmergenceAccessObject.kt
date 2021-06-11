package com.saliktariq.archelon.datalayer.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.saliktariq.archelon.datalayer.entity.AdultEmergence
@Dao
interface AdultEmergenceAccessObject {

    //Query to insert AdultEmergence object
    @Insert
    fun insertAdultEmergenceData(adultEmergence: AdultEmergence)

    //Query to retrieve ActivitySequence object based on ActivitySequenceID
    @Query("Select * from AdultEmergence where adultEmergenceID = :key LIMIT 1")
    fun adultEmergenceDataByAdultEmergenceID(key: Long): AdultEmergence?
}