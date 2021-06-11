package com.saliktariq.archelon.ui.newsurvey

import androidx.lifecycle.ViewModel
import com.saliktariq.archelon.datalayer.parcelables.MorningSurveyBeachDateTime

class StartNewSurveyViewModel : ViewModel() {

    //Variable to hold data for start new survey screen
    var morningSurveyScreenOneData: MorningSurveyBeachDateTime? = null

    //Variables to hold UI object values
    var beach: String = ""
    var sector: String = ""
    var dayOfTheMonth: Int = 0
    var month: Int = 0 //Months start from 0, i.e., January is 0
    var year: Int = 0
    var hour: Int = 0 //Always showing in 24 hours time
    var minute: Int = 0

    override fun onCleared() {
        super.onCleared()
        /*Making sure there is no data leak and all data is nullified before moving on
        and the only valid data is in the args which is passed on
         */
        morningSurveyScreenOneData = null
    }


}
