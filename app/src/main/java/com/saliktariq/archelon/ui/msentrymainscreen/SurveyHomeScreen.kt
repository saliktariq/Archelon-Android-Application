package com.saliktariq.archelon.ui.msentrymainscreen

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavArgs
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.saliktariq.archelon.databinding.SurveyHomeScreenFragmentBinding
import com.saliktariq.archelon.datalayer.parcelables.MorningSurveyBeachDateTime
import com.saliktariq.archelon.datalayer.parcelables.MorningSurveyWithObserversWeather
import com.saliktariq.archelon.helper.AppContext
import com.saliktariq.archelon.helper.MyToasts
import com.saliktariq.archelon.ui.newsurvey.ObserversWeatherViewModel
import com.saliktariq.archelon.ui.newsurvey.ObserversWeatherViewModelFactory
import kotlinx.coroutines.launch

class SurveyHomeScreen : Fragment(), View.OnClickListener {

    private var binding: SurveyHomeScreenFragmentBinding? = null
    private val viewBinding get() = binding!!

    //Object to hold args data
    private val args by navArgs<SurveyHomeScreenArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //Getting reference to the binding object and inflating the fragment
        binding = SurveyHomeScreenFragmentBinding.inflate(inflater, container, false)

        // Creating instance of ViewModel Factory
        val viewModelFactory = SurveyHomeScreenViewModelFactory()

        // Get a reference to the ViewModel associated with SurveyHomeScreen Fragment
        val surveyHomeScreenViewModel =
            ViewModelProvider(this, viewModelFactory).get(SurveyHomeScreenViewModel::class.java)

        //Assigning receiving parcelable object to view model variable
        surveyHomeScreenViewModel.morningSurveyScreenOneAndScreenTwoData =
            args.MorningSurveyObserversWeather

        //Set onClick listener on Cancel Survey button
        viewBinding.buttonCancelSurvey.setOnClickListener {

            val cancelSurveyDialog = AlertDialog.Builder(context)
            cancelSurveyDialog.setMessage("Are you sure you want to cancel this event?")
            cancelSurveyDialog.setTitle("Cancel Event")
            cancelSurveyDialog.setCancelable(true)

            cancelSurveyDialog.setPositiveButton(
                "Yes"
            ) { _, _ ->
                viewBinding.root.findNavController()
                    .navigate(SurveyHomeScreenDirections.actionSurveyHomeScreenToHomeScreen())
            }

            cancelSurveyDialog.setNegativeButton(
                "No"
            ) { dialog, _ -> dialog.cancel() }

            val createDialogue = cancelSurveyDialog.create()
            createDialogue.show()

        }

        //Set onClick listener on Add New Event button
        viewBinding.btnAddNewEvent.setOnClickListener {
            viewBinding.root.findNavController().navigate(
                SurveyHomeScreenDirections.actionSurveyHomeScreenToChooseEvent(
                    surveyHomeScreenViewModel.morningSurveyScreenOneAndScreenTwoData
                )
            )
        }

        //Set onClick listener on Previous button
        viewBinding.buttonPrevious.setOnClickListener {

            val secondScreenData = MorningSurveyWithObserversWeather(
                surveyHomeScreenViewModel.morningSurveyScreenOneAndScreenTwoData?.msLeader,
                surveyHomeScreenViewModel.morningSurveyScreenOneAndScreenTwoData?.secondObserver,
                surveyHomeScreenViewModel.morningSurveyScreenOneAndScreenTwoData?.thirdObserver,
                surveyHomeScreenViewModel.morningSurveyScreenOneAndScreenTwoData?.fourthObserver,
                surveyHomeScreenViewModel.morningSurveyScreenOneAndScreenTwoData?.sky,
                surveyHomeScreenViewModel.morningSurveyScreenOneAndScreenTwoData?.precipitation,
                surveyHomeScreenViewModel.morningSurveyScreenOneAndScreenTwoData?.windIntensity,
                surveyHomeScreenViewModel.morningSurveyScreenOneAndScreenTwoData?.windDirection,
                surveyHomeScreenViewModel.morningSurveyScreenOneAndScreenTwoData?.surf,
                null
            )

            //Creating object to include ObserversWeather Fragment UI data in the StartNewSurvey Fragment data variable
            val observersWeatherArgs = MorningSurveyBeachDateTime(
                surveyHomeScreenViewModel.morningSurveyScreenOneAndScreenTwoData?.firstScreenData?.beach,
                surveyHomeScreenViewModel.morningSurveyScreenOneAndScreenTwoData?.firstScreenData?.sector,
                surveyHomeScreenViewModel.morningSurveyScreenOneAndScreenTwoData?.firstScreenData?.dayOfTheMonth,
                surveyHomeScreenViewModel.morningSurveyScreenOneAndScreenTwoData?.firstScreenData?.month,
                surveyHomeScreenViewModel.morningSurveyScreenOneAndScreenTwoData?.firstScreenData?.year,
                surveyHomeScreenViewModel.morningSurveyScreenOneAndScreenTwoData?.firstScreenData?.hour,
                surveyHomeScreenViewModel.morningSurveyScreenOneAndScreenTwoData?.firstScreenData?.minute,
                secondScreenData
            )


            viewBinding.root.findNavController().navigate(
                SurveyHomeScreenDirections.actionSurveyHomeScreenToObserversWeather(
                    observersWeatherArgs
                )
            )
        }

        //Set onclick listener on unimplemented buttons
        viewBinding.btnCheckPreviousEntry.setOnClickListener(this)
        viewBinding.btnCheckNest.setOnClickListener(this)
        viewBinding.btnEmergencyRelocation.setOnClickListener(this)
        viewBinding.btnSurveyRemark.setOnClickListener(this)

        //Set onClick listener on End Survey Button that would submit data in database
        viewBinding.btnEndSurvey.setOnClickListener {
            //Initialising coroutine to submit morning survey data with livecycle scope


            //Making sure a null reference is not passed to the function
            if (surveyHomeScreenViewModel.morningSurveyScreenOneAndScreenTwoData != null) {
                viewLifecycleOwner.lifecycleScope.launch {
                    surveyHomeScreenViewModel.insertMorningSurveyData(
                        surveyHomeScreenViewModel.argsToMorningSurveyObject(
                            surveyHomeScreenViewModel.morningSurveyScreenOneAndScreenTwoData!!
                        )
                    )
                    viewBinding.root.findNavController()
                        .navigate(SurveyHomeScreenDirections.actionGlobalHomeScreen())
                }
            } else {
                MyToasts.showToast("Error! Data not submitted", AppContext.appContext)
            }

        }


        return viewBinding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        //Destroying the view binding
        binding = null
    }

    override fun onClick(v: View?) {
        //This message is to be replaced by actual implementation of buttons i.e., navigation
        MyToasts.showToast("Feature not implemented", AppContext.appContext)
    }

}
