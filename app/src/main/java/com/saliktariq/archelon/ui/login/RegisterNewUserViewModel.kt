package com.saliktariq.archelon.ui.login

import android.os.Build
import androidx.lifecycle.ViewModel
import com.saliktariq.archelon.helper.AppContext
import com.saliktariq.archelon.helper.MyToasts
import com.saliktariq.archelon.helper.SendMail
import com.saliktariq.archelon.repository.Repository


class RegisterNewUserViewModel() : ViewModel() {

    //Local variables to hold form data
    var username: String = ""
    var pwd: String = ""
    var email: String = ""
    var firstName: String = ""
    var authCode: Long = 0L //Used to reset password

    //Variables to hold error flags
    var userAlreadyExists: Boolean = false
    var emailAlreadyExists: Boolean = false

    //Variables to hold data from the datasource
    var retrievedEmailAddress: String? = null
    var retrievedUserName: String? = null


    //Getting instance of Repository
    val repo = Repository.get()

    //Function to retrieve user data based on username
    suspend fun getUserData(username: String) {
        if (username != null && username.length >= 5) {
            val data = repo.getUserData(username)
            if (data != null) {
                retrievedUserName = data.username
                retrievedEmailAddress = data.email
                //checking email length greater than 3 as it is the theoretical smallest email size
                if ((retrievedEmailAddress.equals(email)) && retrievedEmailAddress!!.length > 3) {
                    emailAlreadyExists = true
                }

                //Checking retrieved username length to avoid null entries
                if ((retrievedUserName.equals(username)) && retrievedUserName!!.length > 3) {
                    userAlreadyExists = true
                }
            }
        }


    }

    //Insert data into database using repository
    suspend fun signUpUser() {

        repo.insertAuthenticationData(
            0,
            username,
            pwd.hashCode().toLong(),
            email,
            firstName,
            authCode
        )

        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.Q) {
            //Send email to new user with account details
            val sender = SendMail(
                AppContext.appContext!!,
                email,
                "Your new Archelon account",
                "Dear $firstName,\n\n" +
                        " Your account is successfully registered.\n\n Following are the details of your new account:\n\n\n" +
                        "Username: $username \n" +
                        "Password: $pwd"
            )

            sender.execute()
        } else {
            MyToasts.showToast(
                "Email not enabled on Android API >= 30. Contact administrator",
                AppContext.appContext
            )
        }


    }
}



