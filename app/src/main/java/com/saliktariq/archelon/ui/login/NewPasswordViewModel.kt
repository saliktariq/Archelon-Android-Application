package com.saliktariq.archelon.ui.login

import android.os.Build
import androidx.lifecycle.ViewModel
import com.saliktariq.archelon.helper.AppContext
import com.saliktariq.archelon.helper.AuxiliaryFunctions
import com.saliktariq.archelon.helper.MyToasts
import com.saliktariq.archelon.helper.SendMail
import com.saliktariq.archelon.repository.Repository

class NewPasswordViewModel() : ViewModel() {
    //Variable to contain user email address entered in the Fragment
    lateinit var emailAddress: String

    //Variable to contain authentication code entered in the Fragment
    var authCode: Long? = 0

    //Variable to contain new password entered in the Fragment
    lateinit var newPassword: String

    //Variable to contain repeat password entered in the Fragment
    lateinit var retypeNewPassword: String

    //Variable to check if queries has completed successfully
//    var isValidEmail: Boolean = false
    var isOperationCompleted: Boolean = false

    //Variables to hold data from the datasource
    var retrievedEmailAddress: String? = null
    var retrievedAuthCode: Long = 0
    var retrievedUserName: String? = null
    var retrievedFirstName: String? = null


    //Getting instance of Repository
    val repo = Repository.get()


    //Function to check if password and retype password are equal
    fun passwordEqualsRetypePassword(): Boolean {
        return newPassword.equals(retypeNewPassword)
    }

    //Function to retrieve userdata
    suspend fun getUserData() {
        val data = repo.getUserDataByEmail(emailAddress)
        if (data != null) {
            retrievedAuthCode = data.authCode
            retrievedEmailAddress = data.email
            retrievedFirstName = data.firstName
        }
    }

    //Function to check if entered email is present in the dataset retrieved
    fun emailPresentInDataSource(): Boolean {
        return ((emailAddress.equals(retrievedEmailAddress) && (retrievedEmailAddress != null)))
    }

    //Function to check if entered authorization code is valid code in the dataset retrieved
    fun authCodePresentInDataSource(): Boolean {
        return (authCode == retrievedAuthCode)
    }

    //Function to update new authCode and password for a given email addres
    suspend fun updatePasswordAndAuthCode() {

        //Updating AuthCode for User dataset
        val newAuthCode = AuxiliaryFunctions.generateAuthCode(
            retrievedUserName,
            newPassword,
            emailAddress,
            retrievedFirstName
        )
        repo.updateAuthCode(newAuthCode, emailAddress)

        //Updating password
        repo.updatePassword(newPassword.hashCode().toLong(), emailAddress)

        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.Q) {
            //Sending email to user indicating that their password is reset and sending information about updated account details
            val sender = SendMail(
                AppContext.appContext!!,
                emailAddress!!,
                "Your new password for Archelon",
                "Dear $retrievedFirstName,\n\n" +
                        " Your password is successfully updated.\n\n Please use the following login details to sign into the App:\n\n\n" +
                        "Associated email address: $emailAddress\n\n" +
                        "New password: $newPassword \n"
            )
            sender.execute()
        } else {
            MyToasts.showToast(
                "Email not enabled on Android API >= 30. Contact administrator",
                AppContext.appContext
            )
        }

        //Setting isOperationCompleted to true indicating that the password reset process has succeeded
        isOperationCompleted = true

    }
}
