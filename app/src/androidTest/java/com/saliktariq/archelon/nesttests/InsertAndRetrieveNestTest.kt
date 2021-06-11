package com.saliktariq.archelon.nesttests

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.saliktariq.archelon.datalayer.dao.NestAccessObject
import com.saliktariq.archelon.datalayer.database.ArchelonDB
import com.saliktariq.archelon.datalayer.entity.Nest
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

import java.util.*


@RunWith(AndroidJUnit4::class)
class InsertAndRetrieveNestTest {
    private lateinit var nestDao: NestAccessObject
    private lateinit var database: ArchelonDB

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        database = Room.inMemoryDatabaseBuilder(context, ArchelonDB::class.java)
            .allowMainThreadQueries()
            .build()
        nestDao = database.nestDao


    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        database.close()
    }

    @Test
    @Throws(Exception::class)
    fun insertAndRetrieveData() {
        val nestObject = Nest(1,"NEST","MS",true,true,2L,3L,false,
        true,true,3L,4f,3,4,2,3,5,6,2,5
        ,6,7,4,"A","AA")
        nestDao.insertNestData(nestObject)
        val retrievedNestObject =
            nestDao.getNestByNestID(1)
        Assert.assertEquals(nestObject.nestID,retrievedNestObject?.nestID)

    }
}
