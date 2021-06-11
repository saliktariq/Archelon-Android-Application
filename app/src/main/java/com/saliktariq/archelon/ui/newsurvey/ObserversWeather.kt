package com.saliktariq.archelon.ui.newsurvey

import android.R
import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.saliktariq.archelon.databinding.ObserversWeatherFragmentBinding
import com.saliktariq.archelon.datalayer.parcelables.MorningSurveyBeachDateTime
import com.saliktariq.archelon.datalayer.parcelables.MorningSurveyWithObserversWeather
import com.saliktariq.archelon.helper.AppContext
import com.saliktariq.archelon.helper.MyToasts


class ObserversWeather : Fragment() {

    private var binding: ObserversWeatherFragmentBinding? = null
    private val viewBinding get() = binding!!

    //Variable to hold received args (parcelable object)
    private val args by navArgs<ObserversWeatherArgs>()

    /*
    Setting variable of type array to hold choice of weather spinners
     */
    private var sky = arrayOf("clear", "light overcast", "heavy overcast", "completely overcast")
    private var precipitation = arrayOf("none", "drizzle", "rain", "heavy rain", "storm")
    private var windIntensity = arrayOf("no wind", "weak (breeze)", "medium", "strong", "gale")
    private var windDirection = arrayOf("N", "NE", "E", "SE", "S", "SW", "W", "NW")
    private var surf = arrayOf("calm", "light", "moderate", "rough", "swell")


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //Getting reference to the binding object and inflating the fragment
        binding = ObserversWeatherFragmentBinding.inflate(inflater, container, false)

        // Creating instance of ViewModel Factory
        val viewModelFactory = ObserversWeatherViewModelFactory()

        // Get a reference to the ViewModel associated with ObserversWeather Fragment
        val observersWeatherViewModel =
            ViewModelProvider(this, viewModelFactory).get(ObserversWeatherViewModel::class.java)


        //Defining weather spinner variables to access spinners on the UI
        val skySpinner: Spinner = viewBinding.sky
        val precipitationSpinner: Spinner = viewBinding.precipitation
        val windIntensitySpinner: Spinner = viewBinding.windIntensity
        val windDirectionSpinner: Spinner = viewBinding.windDirection
        val surfSpinner: Spinner = viewBinding.surf

        //Setting arrayAdapter for beachSpinner
        val skyAdapter = ArrayAdapter<String>(
            this.requireActivity(),
            R.layout.simple_spinner_item, sky
        )
        val precipitationAdapter = ArrayAdapter<String>(
            this.requireActivity(),
            R.layout.simple_spinner_item, precipitation
        )
        val windIntensityAdapter = ArrayAdapter<String>(
            this.requireActivity(),
            R.layout.simple_spinner_item, windIntensity
        )
        val windDirectionAdapter = ArrayAdapter<String>(
            this.requireActivity(),
            R.layout.simple_spinner_item, windDirection
        )
        val surfAdapter = ArrayAdapter<String>(
            this.requireActivity(),
            R.layout.simple_spinner_item, surf
        )

        //Setting drop down view resource to adapters
        skyAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)
        precipitationAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)
        windIntensityAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)
        windDirectionAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)
        surfAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)

        //attaching adapters to spinners
        skySpinner.setAdapter(skyAdapter)
        precipitationSpinner.setAdapter(precipitationAdapter)
        windIntensitySpinner.setAdapter(windIntensityAdapter)
        windDirectionSpinner.setAdapter(windDirectionAdapter)
        surfSpinner.setAdapter(surfAdapter)


        //Assigning data received from Morning Survey Start page to ObserverWeatherViewModel variable
        if (args.StartNewSurveyData != null) {
            observersWeatherViewModel.screenOneData = args.StartNewSurveyData
            observersWeatherViewModel.screenTwoData = args.StartNewSurveyData?.screentwoData

            //Updating UI with the received data that was previously entered before navigating
            viewBinding.txtMSLeader.setText(observersWeatherViewModel.screenTwoData?.msLeader)
            viewBinding.txtSecondObserver.setText(observersWeatherViewModel.screenTwoData?.secondObserver)
            viewBinding.txtThirdObserver.setText(observersWeatherViewModel.screenTwoData?.thirdObserver)
            viewBinding.editText4thObs.setText(observersWeatherViewModel.screenTwoData?.fourthObserver)


            /*
            Using Runnables to take load off the main thread for spinner selection and queries
             */

            //Setting Sky Spinner to previously selected option
            val skySelected = observersWeatherViewModel.screenTwoData?.sky
            val indexOfSky = sky.indexOf(skySelected)
            skySpinner.post(Runnable { skySpinner.setSelection(indexOfSky) })

            //Setting Precipitation Spinner to previously selected option
            val precipitationSelected = observersWeatherViewModel.screenTwoData?.precipitation
            val indexOfPrecipitation = precipitation.indexOf(precipitationSelected)
            precipitationSpinner.post(Runnable {
                precipitationSpinner.setSelection(
                    indexOfPrecipitation
                )
            })

            //Setting windIntensitySpinner to previously selected option
            val windIntensitySelected = observersWeatherViewModel.screenTwoData?.windIntensity
            val indexOfWindIntensity = windIntensity.indexOf(windIntensitySelected)
            windIntensitySpinner.post(Runnable {
                windIntensitySpinner.setSelection(
                    indexOfWindIntensity
                )
            })

            //Setting windDirectionspinner to previously selected option
            val windDirectionSelected = observersWeatherViewModel.screenTwoData?.windDirection
            val indexOfWindDirection = windDirection.indexOf(windDirectionSelected)
            windDirectionSpinner.post(Runnable {
                windDirectionSpinner.setSelection(
                    indexOfWindDirection
                )
            })

            //Setting Surf Spinner to previously selected option
            val surfSelected = observersWeatherViewModel.screenTwoData?.surf
            val indexOfSurf = surf.indexOf(surfSelected)
            surfSpinner.post(Runnable { surfSpinner.setSelection(indexOfSurf) })
        }


        //Set onClick listener on Back arrow
        viewBinding.backArrowtoSurveyHome.setOnClickListener {
            viewBinding.root.findNavController().navigate(
                ObserversWeatherDirections.actionObserversWeatherToStartNewSurvey(
                    observersWeatherViewModel.screenOneData
                )
            )
        }

        //Set onClick listener on Previous button
        viewBinding.buttonPrevious.setOnClickListener {
            viewBinding.root.findNavController().navigate(
                ObserversWeatherDirections.actionObserversWeatherToStartNewSurvey(
                    observersWeatherViewModel.screenOneData
                )
            )
        }

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
                    .navigate(ObserversWeatherDirections.actionObserversWeatherToHomeScreen())
            }

            cancelSurveyDialog.setNegativeButton(
                "No"
            ) { dialog, _ -> dialog.cancel() }

            val createDialogue = cancelSurveyDialog.create()
            createDialogue.show()

        }

        //Set onClick listener on Next button
        viewBinding.buttonNext.setOnClickListener {

            //Setting viewModel variables to UI fields
            observersWeatherViewModel.msLeader = viewBinding.txtMSLeader.text.toString()
            observersWeatherViewModel.secondObserver = viewBinding.txtSecondObserver.text.toString()
            observersWeatherViewModel.thirdObserver = viewBinding.txtThirdObserver.text.toString()
            observersWeatherViewModel.fourthObserver = viewBinding.editText4thObs.text.toString()
            observersWeatherViewModel.sky = skySpinner.selectedItem.toString()
            observersWeatherViewModel.precipitation = precipitationSpinner.selectedItem.toString()
            observersWeatherViewModel.windIntensity = windIntensitySpinner.selectedItem.toString()
            observersWeatherViewModel.windDirection = windDirectionSpinner.selectedItem.toString()
            observersWeatherViewModel.surf = surfSpinner.selectedItem.toString()

            //Setting parcelable variable screenTwoData in the viewModel
            observersWeatherViewModel.screenTwoData = MorningSurveyWithObserversWeather(
                observersWeatherViewModel.msLeader,
                observersWeatherViewModel.secondObserver,
                observersWeatherViewModel.thirdObserver,
                observersWeatherViewModel.fourthObserver,
                observersWeatherViewModel.sky,
                observersWeatherViewModel.precipitation,
                observersWeatherViewModel.windIntensity,
                observersWeatherViewModel.windDirection,
                observersWeatherViewModel.surf,
                observersWeatherViewModel.screenOneData
            )

            //Checking if atleast one observer details are entered before proceeding. Allowing atleast 2 character initials to count as a valid name
            if ((observersWeatherViewModel.msLeader.length > 1) || (observersWeatherViewModel.secondObserver.length > 1) || (observersWeatherViewModel.thirdObserver.length > 1)
                || (observersWeatherViewModel.fourthObserver.length > 1)
            ) {
                viewBinding.root.findNavController().navigate(
                    ObserversWeatherDirections.actionObserversWeatherToSurveyHomeScreen(
                        observersWeatherViewModel.screenTwoData
                    )
                )

            } else {
                MyToasts.showToast(
                    "Please fill in atleast one Observer or MS Leader to proceed!",
                    AppContext.appContext
                )
            }
        }

        return viewBinding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        //Destroying the view binding
        binding = null
    }
}
