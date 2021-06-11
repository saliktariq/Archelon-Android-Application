package com.saliktariq.archelon.ui.msentrymainscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.saliktariq.archelon.ui.newsurvey.ObserversWeatherViewModel
import java.lang.IllegalArgumentException

class SurveyHomeScreenViewModelFactory: ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SurveyHomeScreenViewModel::class.java)){
            return SurveyHomeScreenViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}