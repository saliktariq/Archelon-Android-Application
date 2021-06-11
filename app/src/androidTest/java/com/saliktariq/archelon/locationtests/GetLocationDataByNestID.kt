package com.saliktariq.archelon.locationtests

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.saliktariq.archelon.datalayer.dao.LocationAccessObject
import com.saliktariq.archelon.datalayer.database.ArchelonDB
import com.saliktariq.archelon.datalayer.entity.Location
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class GetLocationDataByNestID {
    private lateinit var locationDao: LocationAccessObject
    private lateinit var database: ArchelonDB

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        database = Room.inMemoryDatabaseBuilder(context, ArchelonDB::class.java)
            .allowMainThreadQueries()
            .build()
        locationDao = database.locationDao


    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        database.close()
    }

    @Test
    @Throws(Exception::class)
    fun getLocationDataByNestID() {
        val locationObject = Location(1L,2.2f,2.3f,3.4f,4.4f,1.2f,4.3f,"a","b","c",0)
        locationDao.insertLocationData(locationObject)
        val retrievedLocationObject =
            locationDao.getLocationDataWithNestID(0)
        Assert.assertEquals(retrievedLocationObject?.get(0), locationObject)

    }
}