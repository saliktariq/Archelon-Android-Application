package com.saliktariq.archelon.datalayer.parcelables

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MorningSurveyBeachDateTime(
    var beach: String?,
    var sector: String?,
    var dayOfTheMonth: Int?,
    var month: Int?,
    var year: Int?,
    var hour: Int?,
    var minute: Int?,
    var screentwoData: MorningSurveyWithObserversWeather?
/*
    This variable will be null in forward navigation,
    and will only be used in reverse navigation back to ObserversWeatherFragment
    in order to repopulate the data on previous screen
 */
) : Parcelable