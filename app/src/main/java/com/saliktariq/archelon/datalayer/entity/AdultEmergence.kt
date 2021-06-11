package com.saliktariq.archelon.datalayer.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class AdultEmergence (
    @PrimaryKey(autoGenerate = true)
    var adultEmergenceID: Long = 0,
    var morningSurveyID: Long?,
    var suspectedNest: Boolean?,
    var noEggsFound: Boolean?,
    /*
    Possible values for variable: trackType
    0: A
    1: B
    2: C
    3: D
    4: E
     */
    var trackType: Int?,
    var lmb: Boolean?,
    var rmb: Boolean?,
    var thirdBM: Boolean?,
    var areaDiagram: ByteArray?,
    var LocationID: Long?,
    var activitySequenceID: Long?,
    var photoID: Long?
)