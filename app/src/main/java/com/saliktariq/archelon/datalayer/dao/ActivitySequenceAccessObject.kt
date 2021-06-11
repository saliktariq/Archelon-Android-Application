package com.saliktariq.archelon.datalayer.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.saliktariq.archelon.datalayer.entity.ActivitySequence
@Dao
interface ActivitySequenceAccessObject {

    //Query to insert ActivitySequence object
    @Insert
    fun insertActivitySequenceData(activitySequence: ActivitySequence)

    //Query to retrieve ActivitySequence object based on ActivitySequenceID
    @Query("Select * from ActivitySequence where ActivitySequenceID = :key LIMIT 1")
    fun activitySequenceDataByActivitySequenceID(key: Long): ActivitySequence?
}