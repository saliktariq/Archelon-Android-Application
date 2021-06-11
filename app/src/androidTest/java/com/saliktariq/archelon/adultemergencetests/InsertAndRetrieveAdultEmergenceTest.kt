package com.saliktariq.archelon.adultemergencetests


import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.saliktariq.archelon.datalayer.dao.AdultEmergenceAccessObject
import com.saliktariq.archelon.datalayer.database.ArchelonDB
import com.saliktariq.archelon.datalayer.entity.AdultEmergence

import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class InsertAndRetrieveAdultEmergenceTest {
    private lateinit var adultEmergenceDao: AdultEmergenceAccessObject
    private lateinit var database: ArchelonDB

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        database = Room.inMemoryDatabaseBuilder(context, ArchelonDB::class.java)
            .allowMainThreadQueries()
            .build()
        adultEmergenceDao = database.adultEmergenceDao


    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        database.close()
    }

    @Test
    @Throws(Exception::class)
    fun insertAndRetrieveEmergenceData() {
        val adultEmergenceObject = AdultEmergence(1,2L,false,false,2,false,true,true,null,3L,4L,5L)
        adultEmergenceDao.insertAdultEmergenceData(adultEmergenceObject)
        val retrievedAdultEmergenceObject =
            adultEmergenceDao.adultEmergenceDataByAdultEmergenceID(1)

        Assert.assertSame(retrievedAdultEmergenceObject?.adultEmergenceID, adultEmergenceObject.adultEmergenceID)
        Assert.assertEquals(retrievedAdultEmergenceObject?.morningSurveyID, adultEmergenceObject.morningSurveyID)
        Assert.assertEquals(retrievedAdultEmergenceObject?.suspectedNest, adultEmergenceObject.suspectedNest)
        Assert.assertEquals(retrievedAdultEmergenceObject?.noEggsFound, adultEmergenceObject.noEggsFound)
        Assert.assertEquals(retrievedAdultEmergenceObject?.trackType, adultEmergenceObject.trackType)
        Assert.assertEquals(retrievedAdultEmergenceObject?.lmb, adultEmergenceObject.lmb)
        Assert.assertEquals(retrievedAdultEmergenceObject?.rmb, adultEmergenceObject.rmb)
        Assert.assertEquals(retrievedAdultEmergenceObject?.thirdBM, adultEmergenceObject.thirdBM)
        Assert.assertEquals(retrievedAdultEmergenceObject?.areaDiagram, adultEmergenceObject.areaDiagram)
        Assert.assertEquals(retrievedAdultEmergenceObject?.LocationID, adultEmergenceObject.LocationID)
        Assert.assertEquals(retrievedAdultEmergenceObject?.activitySequenceID, adultEmergenceObject.activitySequenceID)
        Assert.assertEquals(retrievedAdultEmergenceObject?.photoID, adultEmergenceObject.photoID)

    }
}