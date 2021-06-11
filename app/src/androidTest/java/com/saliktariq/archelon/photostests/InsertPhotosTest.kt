package com.saliktariq.archelon.photostests

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.saliktariq.archelon.datalayer.dao.PhotosAccessObject
import com.saliktariq.archelon.datalayer.database.ArchelonDB
import com.saliktariq.archelon.datalayer.entity.Photos
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class InsertPhotosTest {
    private lateinit var photosDao: PhotosAccessObject
    private lateinit var database: ArchelonDB

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        database = Room.inMemoryDatabaseBuilder(context, ArchelonDB::class.java)
            .allowMainThreadQueries()
            .build()
        photosDao = database.photosDao


    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        database.close()
    }

    @Test
    @Throws(Exception::class)
    fun insertAndGetPhotoData() {
        val newPhotoData = Photos(1L, "testphotoname", 100L, null)
        photosDao.insertPhotoData(newPhotoData)
        val photoData = photosDao.getAllPhotoData(1)

        Assert.assertEquals(photoData?.photoID, 1L)
        Assert.assertEquals(photoData?.photoFileName, "testphotoname")
        Assert.assertEquals(photoData?.nestID, 100L)
        Assert.assertEquals(photoData?.photo, null)

    }
}