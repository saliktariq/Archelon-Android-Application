package com.saliktariq.archelon.helper

class AuxiliaryFunctions {
    companion object {
        //Function to generate authCode that will be used to reset the password
        fun generateAuthCode(
            username: String?,
            pwd: String,
            email: String,
            firstName: String?
        ): Long {

            val userHash = username.hashCode()
            val pwdHash = pwd.hashCode()
            val emailHash = email.hashCode()
            val firstNameHash = firstName.hashCode()
            val currentTime =
                System.currentTimeMillis() //Adding a random unique variable to the hash mix

            var hashCode = userHash + pwdHash + emailHash + firstNameHash + currentTime
            val stringLength = hashCode.toString().length
            val targetLength: Int

            //Managing the length of hashCode
            //Update this variable to control the length of auth code.
            val codeLength = 13
            targetLength = if (stringLength < codeLength) {
                stringLength
            } else {
                codeLength
            }

            //Setting the desired length of the hashCode, and returning it as a Long
            hashCode = (hashCode.toString().substring(0, targetLength)).toLong()
            return hashCode
        }
    }
}