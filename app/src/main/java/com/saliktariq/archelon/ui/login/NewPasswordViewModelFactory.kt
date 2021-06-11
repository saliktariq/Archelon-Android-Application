package com.saliktariq.archelon.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class NewPasswordViewModelFactory () : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NewPasswordViewModel::class.java)){
            return NewPasswordViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}