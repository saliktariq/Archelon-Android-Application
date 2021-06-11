package com.saliktariq.archelon.datalayer.parcelables

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MorningSurveyWithObserversWeather (
    var msLeader: String?,
    var secondObserver: String?,
    var thirdObserver: String?,
    var fourthObserver: String?,
    var sky: String?,
    var precipitation: String?,
    var windIntensity: String?,
    var windDirection: String?,
    var surf: String?,
    var firstScreenData: MorningSurveyBeachDateTime?
) : Parcelable
