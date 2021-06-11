package com.saliktariq.archelon.repositorytests

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.saliktariq.archelon.datalayer.dao.ActivitySequenceAccessObject
import com.saliktariq.archelon.datalayer.database.ArchelonDB
import com.saliktariq.archelon.datalayer.entity.ActivitySequence
import com.saliktariq.archelon.repository.Repository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class ActivitySequenceRepositoryTest {

    lateinit var retrievedActivitySequenceObject: ActivitySequence

    @Test
    @Throws(Exception::class)
    fun insertAndRetrieveData() {
        val activitySequenceObject = ActivitySequence(0, 2, 3L, 4L)
        val repo = Repository.get()
        GlobalScope.launch {
            val dataInserted = GlobalScope.launch {
                repo.insertActivitySequence(activitySequenceObject)
            }
            dataInserted.join() //Waiting for dataInserted to be executed before proceeding

            val dataRetrieved = GlobalScope.launch {
                delay(5000) //Intentional delay so that data is inserted before retrieval
                retrievedActivitySequenceObject = repo.activitySequenceDataByActivitySequenceID(0)!!
            }
            dataRetrieved.join() //Waiting for data to be retrieved before executing Assert.assertEquals

            Assert.assertEquals(activitySequenceObject, retrievedActivitySequenceObject)
        }


    }
}