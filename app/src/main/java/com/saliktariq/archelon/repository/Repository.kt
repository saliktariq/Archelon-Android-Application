package com.saliktariq.archelon.repository


import android.content.Context
import com.saliktariq.archelon.datalayer.database.ArchelonDB
import com.saliktariq.archelon.datalayer.entity.*
import com.saliktariq.archelon.helper.AppContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class Repository private constructor(context: Context) {

    /*
    DECLARATION:
    Code (Lines 20-31) for repository to act as *singleton* implemented following instructions from
    The Big Nerd Ranch Guide at
    https://learning.oreilly.com/library/view/android-programming-the/9780135257555/ch11s04.html
     */
    companion object {
        private var INSTANCE: Repository? = null

        fun initialize(context: Context) {
            if (INSTANCE == null) {
                INSTANCE = Repository(context)
            }
        }

        fun get(): Repository {
            return INSTANCE ?: throw IllegalStateException("Repository not initialized")
        }
    }


    private val database: ArchelonDB = ArchelonDB.getInstance(AppContext.appContext!!)

    // DAOs binded to DAOs defined in database class to access functions inside relative DAOs
    private val daoAS = database.activitySequenceDao
    private val daoAE = database.adultEmergenceDao
    private val daoA = database.authenticationDao
    private val daoL = database.locationDao
    private val daoMS = database.morningSurveyDao
    private val daoN = database.nestDao
    private val daoP = database.photosDao


    //Function to insert ActivitySequence object in the data source
    suspend fun insertActivitySequence(activitySequence: Any) = withContext(Dispatchers.IO) {

        if (activitySequence is ActivitySequence) {
            daoAS.insertActivitySequenceData(activitySequence)
        } else {
            throw IllegalArgumentException()
        }

    }

    //Function to retrieve ActivitySequence object based on ActivitySequenceID
    suspend fun activitySequenceDataByActivitySequenceID(key: Long): ActivitySequence? =
        withContext(Dispatchers.IO) {
            return@withContext daoAS.activitySequenceDataByActivitySequenceID(key)
        }

    //Function to insert AdultEmergence object in the datasource
    suspend fun insertAdultEmergenceData(adultEmergence: AdultEmergence) = withContext(Dispatchers.IO) {


            daoAE.insertAdultEmergenceData(adultEmergence)

    }

    //Function to retrieve AdultEmergence object based on AdultEmergenceID
    suspend fun adultEmergenceDataByAdultEmergenceID(key: Long): AdultEmergence? =
        withContext(Dispatchers.IO) {
            return@withContext daoAE.adultEmergenceDataByAdultEmergenceID(key)
        }

    //Function to insert Location information in Location data source
    suspend fun insertLocationData(location: Location) = withContext(Dispatchers.IO) {

            daoL.insertLocationData(location)

    }

    //Function to retrieve Location object based on locationID
    suspend fun getLocationDataWithID(key: Long): Location? = withContext(Dispatchers.IO) {
        return@withContext daoL.getLocationDataWithID(key)
    }

    //Function to retrieve Location object using nestID
    suspend fun getLocationDataWithNestID(key: Long): List<Location>? =
        withContext(Dispatchers.IO) {
            return@withContext daoL.getLocationDataWithNestID(key)
        }

    //Function to insert MorningSurvey object in data source
    suspend fun insertMorningSurveyData(morningSurvey: MorningSurvey) = withContext(Dispatchers.IO) {

            daoMS.insertMorningSurveyData(morningSurvey)

    }

    //Function to retrieve MorningSurvey object using MorningSurveyID
    suspend fun getMorningSurveyDataByMorningSurveyID(key: Long): MorningSurvey? =
        withContext(Dispatchers.IO) {
            return@withContext daoMS.getMorningSurveyDataByMorningSurveyID(key)
        }

    //Function to insert Nest object in data source
    suspend fun insertNestData(nest: Nest) = withContext(Dispatchers.IO) {

            daoN.insertNestData(nest)

    }

    //Function to retrieve Nest object using nestID
    suspend fun getNestByNestID(key: Long): Nest? = withContext(Dispatchers.IO) {
        return@withContext daoN.getNestByNestID(key)
    }

    //Function to insert Photos object in data source
    suspend fun insertPhotoData(photo: Photos) = withContext(Dispatchers.IO) {

            daoP.insertPhotoData(photo)

    }


    //Function to retrieve Photos object from data source by photoID
    suspend fun getAllPhotoData(key: Long): Photos? = withContext(Dispatchers.IO) {
        return@withContext daoP.getAllPhotoData(key)
    }

    //Function to retrieve all photos related to a particular nestID
    suspend fun getNestPhotos(key: Long): List<ByteArray>? = withContext(Dispatchers.IO) {
        return@withContext daoP.getNestPhotos(key)
    }

    //Function to retrieve photos file names for a particular nestID
    suspend fun getNestPhotoFileNames(key: Long): List<String>? = withContext(Dispatchers.IO) {
        return@withContext daoP.getNestPhotoFileNames(key)
    }

    //Function to register a new user to system
    suspend fun insertAuthenticationData(
        uid: Long,
        username: String,
        password: Long,
        email: String,
        firstname: String,
        authCode: Long
    ) = withContext(Dispatchers.IO) {
        val data = Authentication(uid, username, password, email, firstname, authCode)
        daoA.insertAuthenticationData(data)
    }

    //Function to retrieve all data based on email
    suspend fun getUserDataByEmail(email: String?): Authentication? = withContext(Dispatchers.IO) {
        return@withContext daoA.getUserDataByEmail(email)
    }


    //Function to update user password based on email address
    suspend fun updatePassword(newPassword: Long, email: String) = withContext(Dispatchers.IO) {
        daoA.updatePassword(newPassword, email)
    }

    //Function to update user authCode based on email address
    suspend fun updateAuthCode(newAuthCode: Long, email: String) = withContext(Dispatchers.IO) {
        daoA.updateAuthCode(newAuthCode, email)
    }

    // Function to retrieve all data based on username
    suspend fun getUserData(key: String): Authentication? = withContext(Dispatchers.IO) {
        return@withContext daoA.getUserData(key)
    }

    // Function to retrieve password based on username
    suspend fun getPassword(key: String): Long? = withContext(Dispatchers.IO) {
        return@withContext daoA.getPassword(key)
    }

    // Function to retrieve authCode based on given email
    suspend fun getAuthCodeBasedOnEmail(key: String): Long = withContext(Dispatchers.IO) {
        return@withContext daoA.getAuthCodeBasedOnEmail(key)
    }

    //Function to update password and authcode simultaneously in one transaction
    suspend fun updatePasswordAndAuthCode(email: String, password: Long, authCode: Long) =
        withContext(Dispatchers.IO) {

            daoA.updatePassword(
                password,
                email
            )

            daoA.updateAuthCode(
                authCode,
                email
            )

        }
}