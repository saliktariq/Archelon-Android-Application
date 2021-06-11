package com.saliktariq.archelon.datalayer.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

import com.saliktariq.archelon.datalayer.entity.MorningSurvey
@Dao
interface MorningSurveyAccessObject {

    //Query to insert MorningSurvey object
    @Insert
    fun insertMorningSurveyData(morningSurvey: MorningSurvey)

    //Query to retrieve MorningSurvey object using MorningSurveyID
    @Query("Select * from MorningSurvey where morningSurveyID = :key LIMIT 1")
    fun getMorningSurveyDataByMorningSurveyID(key: Long): MorningSurvey?
}