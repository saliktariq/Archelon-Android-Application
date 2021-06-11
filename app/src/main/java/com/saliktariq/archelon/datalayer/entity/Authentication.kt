package com.saliktariq.archelon.datalayer.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "authentication")
data class Authentication(
    @PrimaryKey(autoGenerate = true)
    var uid: Long,
    var username: String,
    //Iteration 2: Convert password to Long for hashcode
//    var pwd: String,
    var pwd: Long,
    var email: String,
    var firstName: String,
    var authCode: Long  //Used to reset password
)