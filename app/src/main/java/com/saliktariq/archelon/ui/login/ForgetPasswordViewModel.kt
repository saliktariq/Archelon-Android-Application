package com.saliktariq.archelon.ui.login

import android.os.Build
import androidx.lifecycle.ViewModel
import com.saliktariq.archelon.helper.AppContext
import com.saliktariq.archelon.helper.MyToasts
import com.saliktariq.archelon.helper.SendMail
import com.saliktariq.archelon.repository.Repository

/**
 * ViewModel for ForgetPassword fragment
 */
class ForgetPasswordViewModel() : ViewModel() {

    //Variable to contain user email address
    lateinit var emailAddress: String
    var isValidEmail: Boolean = false

    //Getting instance of Repository
    val repo = Repository.get()

    //Getting dataset (Authentication) based on email address
    suspend fun getUserData() {
        repo.getUserDataByEmail(emailAddress)
    }

    //Send Password reset code to email
    suspend fun sendAuthCode() {
        val userData = repo.getUserDataByEmail(emailAddress)
        //Checking if requested data is actually fetched
        if (userData != null) {
            //Retrieving authCode from the fetched dataset
            val authCode = userData.authCode

            if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.Q) {
                //Send email to new user with account details
                val sender = SendMail(
                    AppContext.appContext!!,
                    emailAddress,
                    "Your new password reset code",
                    "Dear ${userData.firstName},\n\n" +
                            " This is your one time password reset code.\n\n Please use the following code to reset your password.:\n\n\n" +
                            "Associated email address: $emailAddress\n\n" +
                            "Password reset code: $authCode \n"
                )
                sender.execute()
            } else {
                MyToasts.showToast(
                    "Contact admin at info@archelon.saliktariq.com to retrieve password! Email disabled for API 30+",
                    AppContext.appContext
                )
            }

            //Setting isValidEmail to true indicating that the email entered by user was valid
            isValidEmail = true

        }
    }

    override fun onCleared() {
        super.onCleared()
        //Making sure following variables are reset to avoid subsequent unwanted processing while
        //fragment is not destroyed
        emailAddress = ""
        isValidEmail = false

    }
}
