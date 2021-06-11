package com.saliktariq.archelon.datalayer.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ActivitySequence(
    @PrimaryKey(autoGenerate = true)
    var activitySequenceID: Long = 0,
    /*
    Possible values for variable: activityType
    0: N/A
    1: BP
    2: AEC
    3: S
    4: S->BP
    5: S->AEC
    6: NEST
    7: S->NEST
     */
    var activityType: Int?,
    var nestID: Long?, //Maybe null if activity is not related to any active / known / new nest
    var emergenceID: Long?

)