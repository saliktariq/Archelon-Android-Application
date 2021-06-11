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
class InsertAndGetUserDataTest{
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
    fun insertAndGetUserData() {
        val newUserData = Authentication(1L,"saliktariq","password".hashCode().toLong(),"mtariq01@dcs.bbk.ac.uk","Salik",7500L)
        GlobalScope.launch {
            authenticationDao.insertAuthenticationData(newUserData)
            val userData = authenticationDao.getUserData("saliktariq")

            assertEquals( 1L,userData?.uid)
            assertEquals( "saliktariq",userData?.username)
            assertEquals( "password".hashCode().toLong(),userData?.pwd)
            assertEquals( "mtariq01@dcs.bbk.ac.uk",userData?.email)
            assertEquals( "Salik",userData?.firstName)
            assertEquals( 7500L,userData?.authCode)
        }

    }
}