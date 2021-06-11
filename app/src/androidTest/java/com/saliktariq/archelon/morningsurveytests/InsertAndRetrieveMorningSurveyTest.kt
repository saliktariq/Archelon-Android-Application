package com.saliktariq.archelon.morningsurveytests

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.saliktariq.archelon.datalayer.dao.MorningSurveyAccessObject
import com.saliktariq.archelon.datalayer.database.ArchelonDB
import com.saliktariq.archelon.datalayer.entity.MorningSurvey
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

import java.util.*


@RunWith(AndroidJUnit4::class)
class InsertAndRetrieveMorningSurveyTest {
    private lateinit var morningSurveyDao: MorningSurveyAccessObject
    private lateinit var database: ArchelonDB

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        database = Room.inMemoryDatabaseBuilder(context, ArchelonDB::class.java)
            .allowMainThreadQueries()
            .build()
        morningSurveyDao = database.morningSurveyDao


    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        database.close()
    }

    @Test
    @Throws(Exception::class)
    fun insertAndRetrieveData() {
        val morningSurveyObject = MorningSurvey(1,1,2, 2,2,2020,12,30,"Salik","Tariq","Irina","Grigoras",1,1,2,1,0,0,1)
        morningSurveyDao.insertMorningSurveyData(morningSurveyObject)
        val retrievedMorningSurveyObject =
            morningSurveyDao.getMorningSurveyDataByMorningSurveyID(1)
        Assert.assertEquals(morningSurveyObject,retrievedMorningSurveyObject)

    }
}