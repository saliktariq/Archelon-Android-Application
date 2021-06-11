package com.saliktariq.archelon.authenticationtests

import org.junit.Assert.assertEquals
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.saliktariq.archelon.datalayer.dao.AuthenticationAccessObject
import com.saliktariq.archelon.datalayer.database.ArchelonDB
import com.saliktariq.archelon.datalayer.entity.Authentication
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class GetPasswordTest{
    private lateinit var authenticationDao: AuthenticationAccessObject
    private lateinit var database: ArchelonDB
    @Test
    @Throws(Exception::class)



    @Before
    fun createDb(){
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        database = Room.inMemoryDatabaseBuilder(context, ArchelonDB::class.java)
            .allowMainThreadQueries()
            .build()
        authenticationDao = database.authenticationDao


    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        database.close()
    }
    fun getPassword() {
        val newUserData = Authentication(1,"saliktariq","password".hashCode().toLong(),"mtariq01@dcs.bbk.ac.uk","Salik",7500)
        GlobalScope.launch {
            authenticationDao.insertAuthenticationData(newUserData)
            val userData = authenticationDao.getPassword("saliktariq")

            assertEquals( "password".hashCode().toLong(),userData)
        }


    }
}