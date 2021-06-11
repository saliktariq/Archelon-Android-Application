package com.saliktariq.archelon.datalayer.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

/*
This class defines MorningSurvey table
 */

@Entity
data class MorningSurvey(

    @PrimaryKey(autoGenerate = true)
    var morningSurveyID: Long,

    /*
    Possible values for variable: beach
    0: Mavrovouni
    1: Selinitsa
    2: Vathi
    3: Valtaki
     */
    var beach: Int?,

    /*
    Possible values for variable: sector
    0: East
    1: West
     */
    var sector: Int?,
    var dayOftheMonth: Int?,
    var month: Int?,
    var year: Int?,
    var hour: Int?,
    var minute: Int?,
    var msLeader: String?,
    var secondObserver: String?,
    var thirdObserver: String?,
    var fourthObserver: String?,

    /*
    Possible values for variable: sky
    0: Clear
    1: Light overcast
    2: Heavy overcast
    3: Completely overcast
     */
    var sky: Int?,

    /*
    Possible values for variable: precipitation
    0: None
    1: Drizzle
    2: Rain
    3: Heavy rain
    4: Storm
     */
    var precipitation: Int?,

    /*
    Possible values for variable: windIntensity
    0: No wind
    1: Weak (breeze)
    2: Medium
    3: Strong
    4: Gale
     */
    var windIntensity: Int?,

    /*
    Possible values for variable: windDirection
    0: N
    1: NE
    2: E
    3: SE
    4: S
    5: SW
    6: W
    7: NW
     */
    var windDirection: Int?,

    /*
    Possible values for variable: surf
    0: Calm
    1: Light
    2: Moderate
    3: Rough
    4: Swell
     */
    var surf: Int?,
    var nestID: Long?,
    var emergenceNumber: Int?
)