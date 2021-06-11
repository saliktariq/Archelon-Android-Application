package com.saliktariq.archelon.ui.neweventhome

import androidx.lifecycle.ViewModel
import com.saliktariq.archelon.datalayer.parcelables.MorningSurveyWithObserversWeather

class ChooseEventViewModel: ViewModel() {

    var morningSurveyScreenOneAndScreenTwoData: MorningSurveyWithObserversWeather? = null

    override fun onCleared() {
        super.onCleared()
        /*Making sure there is no data leak and all data is nullified before moving on
        and the only valid data is in the args which is passed on
         */
        morningSurveyScreenOneAndScreenTwoData = null
    }
}