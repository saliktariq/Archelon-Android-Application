package com.saliktariq.archelon.datalayer.dao

import androidx.room.*

import com.saliktariq.archelon.datalayer.entity.Authentication

@Dao
interface AuthenticationAccessObject {

    //Query to register a new user to system
    @Insert
    suspend fun insertAuthenticationData(userData: Authentication)

    //Query to update user password based on email address
    @Query("UPDATE authentication SET pwd = :newPassword WHERE email = :email")
//    fun updatePassword(newPassword: String, email: String)
    fun updatePassword(newPassword: Long, email: String)

    //Query to update user authCode based on email address
    @Query("UPDATE authentication SET authCode = :newAuthCode WHERE email = :email")
    fun updateAuthCode(newAuthCode: Long, email: String)

    // Query to retrieve all data based on username
    @Query("Select * from authentication WHERE username = :key ORDER BY username DESC LIMIT 1")
   suspend fun getUserData(key: String): Authentication?

    // Query to retrieve password based on username
    @Query("Select pwd from authentication WHERE username = :key")
//    fun getPassword(key: String): String?
    fun getPassword(key: String): Long?

    // Query to retrieve authCode based on given email
    @Query("Select authCode from authentication WHERE email = :key ORDER BY email DESC LIMIT 1")
   fun getAuthCodeBasedOnEmail(key: String): Long

    // Query to retrieve all data based on email address
    @Query("Select * from authentication WHERE email = :key ORDER BY email DESC LIMIT 1")
    suspend fun getUserDataByEmail(key: String?): Authentication?

    //Tansaction query to update the password and update new authCode for the user based on email
    @Transaction
    suspend fun updatePasswordAndAuthCode(email: String, password: Long, authCode: Long){
        updatePassword(password, email)
        updateAuthCode(authCode, email)
    }





}