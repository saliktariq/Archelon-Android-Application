package com.saliktariq.archelon.ui.newsurvey

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class ObserversWeatherViewModelFactory: ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ObserversWeatherViewModel::class.java)){
            return ObserversWeatherViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}