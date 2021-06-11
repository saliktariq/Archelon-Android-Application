package com.saliktariq.archelon.repositorytests


import androidx.test.ext.junit.runners.AndroidJUnit4
import com.saliktariq.archelon.repository.Repository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AuthenticationRepositoryTest{
    @Test
    @Throws(Exception::class)
    fun authenticationRepositoryTest() {

        val repo = Repository.get()
        GlobalScope.launch {
            val insertData =  GlobalScope.launch {
                repo.insertAuthenticationData(0,"salik","pass1".hashCode().toLong(),"saliktariq@icloud.com","Salik",12L)}
            insertData.join() //Halting further execution until the data is inserted
            val fetchedPassword = GlobalScope.launch {
                delay(5000) //Intentional delay to let first process finish before second query
                repo.getPassword("salik")
            }
            fetchedPassword.join() //Halting further execution until the data is fetched to compare in assertEquals

            Assert.assertEquals("pass1".hashCode().toLong(),fetchedPassword)
        }

        }






    }