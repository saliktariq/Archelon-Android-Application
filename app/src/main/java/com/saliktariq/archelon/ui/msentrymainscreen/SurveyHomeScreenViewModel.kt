package com.saliktariq.archelon.ui.msentrymainscreen

import androidx.lifecycle.ViewModel
import com.saliktariq.archelon.datalayer.entity.MorningSurvey
import com.saliktariq.archelon.datalayer.parcelables.MorningSurveyWithObserversWeather
import com.saliktariq.archelon.helper.AppContext
import com.saliktariq.archelon.helper.MyToasts
import com.saliktariq.archelon.repository.Repository

class SurveyHomeScreenViewModel : ViewModel() {
    //Variable to hold received parcelable object
    var morningSurveyScreenOneAndScreenTwoData: MorningSurveyWithObserversWeather? = null

    /*Adding nestID and emergenceID for completion purpose and to make the function
    argsToMorningSurveyObject() be able to be reused with Nests and Emergence UI sequences
     */
    var nestID: Long? = 0
    var emergenceNumber: Int? = 0

    /**
     * Function to convert Parcelable Object containing data from 'Start Morning Survey'
     * and 'Observers and Weather' screen. Function takes argument of type
     * MorningSurveyWithObserversWeather that is passed on as SafeArgs to current Fragment.
     * The return type of the function will be an object of type MorningSurvey.
     */

    fun argsToMorningSurveyObject(morningSurveyScreenOneAndScreenTwoData: MorningSurveyWithObserversWeather): MorningSurvey {

        /*
        Possible values for variable: beachID
        0: Mavrovouni
        1: Selinitsa
        2: Vathi
        3: Valtaki
         */
        val beachesArray = arrayOf("Mavrovouni", "Selinitsa", "Vithi", "Valtaki")
        val beachID =
            beachesArray.indexOf(morningSurveyScreenOneAndScreenTwoData.firstScreenData?.beach)

        /*
        Possible values for variable: sectorID
        0: East
        1: West
        */
        val sectorArray = arrayOf("East", "West")
        val sectorID =
            sectorArray.indexOf(morningSurveyScreenOneAndScreenTwoData.firstScreenData?.sector)

        /*
        Months start from 0, January = 0, therefore adding +1 to the value of month
        */
        val month = morningSurveyScreenOneAndScreenTwoData.firstScreenData?.month?.plus(1)

        /*
        Possible values for variable: skyID
        0: Clear
        1: Light overcast
        2: Heavy overcast
        3: Completely overcast
         */
        val skyArray = arrayOf("clear", "light overcast", "heavy overcast", "completely overcast")
        val skyID = skyArray.indexOf(morningSurveyScreenOneAndScreenTwoData.sky)

        /*
        Possible values for variable: precipitationID
        0: None
        1: Drizzle
        2: Rain
        3: Heavy rain
        4: Storm
         */
        val precipitationArray = arrayOf("none", "drizzle", "rain", "heavy rain", "storm")
        val precipitationID =
            precipitationArray.indexOf(morningSurveyScreenOneAndScreenTwoData.precipitation)

        /*
        Possible values for variable: windIntensityID
        0: No wind
        1: Weak (breeze)
        2: Medium
        3: Strong
        4: Gale
         */
        val windIntensityArray = arrayOf("no wind", "weak (breeze)", "medium", "strong", "gale")
        val windIntensityID =
            windIntensityArray.indexOf(morningSurveyScreenOneAndScreenTwoData.windIntensity)

        /*
        Possible values for variable: windDirection
        0: N
        1: NE
        2: E
        3: SE
        4: S
        5: SW
        6: W
        7: NW
         */
        val windDirectionArray = arrayOf("N", "NE", "E", "SE", "S", "SW", "W", "NW")
        val windDirectionID =
            windDirectionArray.indexOf(morningSurveyScreenOneAndScreenTwoData.windDirection)

        /*
        Possible values for variable: surf
        0: Calm
        1: Light
        2: Moderate
        3: Rough
        4: Swell
         */
        val surfArray = arrayOf("calm", "light", "moderate", "rough", "swell")
        val surfID = surfArray.indexOf(morningSurveyScreenOneAndScreenTwoData.surf)


        return MorningSurvey(
            0,
            beachID,
            sectorID,
            morningSurveyScreenOneAndScreenTwoData.firstScreenData?.dayOfTheMonth,
            month,
            morningSurveyScreenOneAndScreenTwoData.firstScreenData?.year,
            morningSurveyScreenOneAndScreenTwoData.firstScreenData?.hour,
            morningSurveyScreenOneAndScreenTwoData.firstScreenData?.minute,
            morningSurveyScreenOneAndScreenTwoData.msLeader,
            morningSurveyScreenOneAndScreenTwoData.secondObserver,
            morningSurveyScreenOneAndScreenTwoData.thirdObserver,
            morningSurveyScreenOneAndScreenTwoData.fourthObserver,
            skyID,
            precipitationID,
            windIntensityID,
            windDirectionID,
            surfID,
            nestID,
            emergenceNumber
        )
    }

    /**
     * Function to submit morning survey data in the data source using Repository
     * Input parameter is MorningSurvey object
     */

    suspend fun insertMorningSurveyData(ms: MorningSurvey) {
        //Retrieving instance of Repository
        val repo = Repository.get()

        //Inserting data into datasource
        repo.insertMorningSurveyData(ms)
        MyToasts.showToast(
            "Morning survey data conducted on ${ms.dayOftheMonth}/${ms.month}/${ms.year} submitted successfully",
            AppContext.appContext
        )
        morningSurveyScreenOneAndScreenTwoData = null
    }

    override fun onCleared() {
        super.onCleared()
        /*Making sure there is no data leak and all data is nullified before moving on
        and the only valid data is in the args which is passed on
         */
        morningSurveyScreenOneAndScreenTwoData = null
    }
}