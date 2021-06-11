package com.saliktariq.archelon.ui.newsurvey

import androidx.lifecycle.ViewModel
import com.saliktariq.archelon.datalayer.parcelables.MorningSurveyBeachDateTime
import com.saliktariq.archelon.datalayer.parcelables.MorningSurveyWithObserversWeather

class ObserversWeatherViewModel : ViewModel() {

    //Variable to hold received parcelable object
    var screenOneData: MorningSurveyBeachDateTime? = null
    var screenTwoData: MorningSurveyWithObserversWeather? = null

    //Variables to hold UI fields data
    var msLeader: String = ""
    var secondObserver: String = ""
    var thirdObserver: String = ""
    var fourthObserver: String = ""
    var sky: String = ""
    var precipitation: String = ""
    var windIntensity: String = ""
    var windDirection: String = ""
    var surf: String = ""

    override fun onCleared() {
        super.onCleared()
        /*Making sure there is no data leak and all data is nullified before moving on
        and the only valid data is in the args which is passed on
         */
        screenOneData = null
        screenTwoData = null
    }
}
