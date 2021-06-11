package com.saliktariq.archelon.ui.neweventhome

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class ChooseEventViewModelFactory:  ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ChooseEventViewModel::class.java)) {
            return ChooseEventViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}