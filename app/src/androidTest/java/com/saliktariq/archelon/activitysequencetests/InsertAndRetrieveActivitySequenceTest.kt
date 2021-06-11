package com.saliktariq.archelon.activitysequencetests

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.saliktariq.archelon.datalayer.dao.ActivitySequenceAccessObject
import com.saliktariq.archelon.datalayer.database.ArchelonDB
import com.saliktariq.archelon.datalayer.entity.ActivitySequence

import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class InsertAndRetrieveActivitySequenceTest{
    private lateinit var activitySequenceDao: ActivitySequenceAccessObject
    private lateinit var database: ArchelonDB

    @Before
    fun createDb(){
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        database = Room.inMemoryDatabaseBuilder(context, ArchelonDB::class.java)
            .allowMainThreadQueries()
            .build()
        activitySequenceDao = database.activitySequenceDao


    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        database.close()
    }

    @Test
    @Throws(Exception::class)
    fun insertAndRetrieveData() {
        val activitySequenceObject = ActivitySequence(1,2,3L,4L)
        activitySequenceDao.insertActivitySequenceData(activitySequenceObject)
        val retrievedActivitySequenceObject = activitySequenceDao.activitySequenceDataByActivitySequenceID(1)

        Assert.assertEquals( activitySequenceObject,retrievedActivitySequenceObject)

    }
}