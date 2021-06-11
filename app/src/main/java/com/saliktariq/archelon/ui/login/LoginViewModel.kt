package com.saliktariq.archelon.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saliktariq.archelon.helper.AppContext
import com.saliktariq.archelon.helper.MyToasts
import com.saliktariq.archelon.repository.Repository
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

/**
 * ViewModel for LoginFragment
 */
class LoginViewModel() : ViewModel() {

    //Variables to hold UI data
    lateinit var uid: String
    lateinit var pwd: String

    //Flag set to true if credentials match
    var authenticated: Boolean = false

    //Initialising repository
    val repo = Repository.get()

    /**
     * Function login to authenticate user to access the application
     * If credentials are correct, true is returned and authenticated
     * variable is set to true
     * @param uid of type String containing username
     * @param pwd of type String containing password
     *
     */

    suspend fun login(uid: String, pwd: String) {
        //Length check to avoid unnecessary I/O call to database
        if (uid.length >= 5 && pwd.length >= 5) {
            val userData = repo.getUserData(uid)
            //If query receives nothing userData is still null
            if (userData == null) {
                authenticated = false
            } else {
                //Comparing password hash with saved hash for password
                if (userData.pwd.equals(pwd.hashCode().toLong())) {
                    authenticated = true
                }
            }


        }
    }

}
