package com.saliktariq.archelon.repositorytests

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.saliktariq.archelon.datalayer.entity.AdultEmergence
import com.saliktariq.archelon.repository.Repository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AdultEmergenceRepositoryTest{
    @Test
    @Throws(Exception::class)
    fun insertAndRetrieveAdultEmergenceID() {
        val adultEmergenceObject = AdultEmergence(0,2L,false,false,2,true,true,true,null,2L,3L,4L)
        lateinit var retrievedAdultEmergenceObject: AdultEmergence

        val repo = Repository.get()

        GlobalScope.launch {
            val insertData = GlobalScope.launch {
                repo.insertAdultEmergenceData(adultEmergenceObject)
            }
            insertData.join() //Halting further execution until insert query is executed
            delay(5000)

            GlobalScope.launch {
                retrievedAdultEmergenceObject = repo.adultEmergenceDataByAdultEmergenceID(0)!!
            }.join() //Halting further execution until data retrieval is completed

                Assert.assertEquals(adultEmergenceObject.morningSurveyID, retrievedAdultEmergenceObject?.morningSurveyID)
                Assert.assertEquals(adultEmergenceObject.activitySequenceID, retrievedAdultEmergenceObject?.activitySequenceID)
                Assert.assertEquals(adultEmergenceObject.trackType, retrievedAdultEmergenceObject?.trackType)
                Assert.assertEquals(adultEmergenceObject.lmb, retrievedAdultEmergenceObject?.lmb)



        }




    }


}