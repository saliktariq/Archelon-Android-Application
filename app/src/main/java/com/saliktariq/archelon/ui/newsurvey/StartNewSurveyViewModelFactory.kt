package com.saliktariq.archelon.ui.newsurvey

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class StartNewSurveyViewModelFactory: ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(StartNewSurveyViewModel::class.java)){
            return StartNewSurveyViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}