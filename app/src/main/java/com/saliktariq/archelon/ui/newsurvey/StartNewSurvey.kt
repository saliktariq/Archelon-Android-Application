package com.saliktariq.archelon.ui.newsurvey

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.DatePicker
import android.widget.Spinner
import android.widget.TimePicker
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.saliktariq.archelon.databinding.StartNewSurveyFragmentBinding
import com.saliktariq.archelon.datalayer.parcelables.MorningSurveyBeachDateTime


class StartNewSurvey : Fragment() {

    private var binding: StartNewSurveyFragmentBinding? = null
    private val viewBinding get() = binding!!

    //Variable to hold received args (parcelable object)
    private val args by navArgs<StartNewSurveyArgs>()


    /*
    Setting variable beaches of type array to hold choice of beaches
     */
    private var beaches = arrayOf("Mavrovouni", "Selinitsa", "Vithi", "Valtaki")

    /*
    Setting variable sector of type array to hold choice of sector
     */
    private var sector = arrayOf("East", "West")


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = StartNewSurveyFragmentBinding.inflate(
            inflater, container, false
        )
        val view = viewBinding.root

        // Creating instance of ViewModel Factory
        val viewModelFactory = StartNewSurveyViewModelFactory()

        // Get a reference to the ViewModel associated with StartNewSurvey
        val startNewSurveyViewModel =
            ViewModelProvider(this, viewModelFactory).get(StartNewSurveyViewModel::class.java)

        //Setting DatePicker
        var datePicker: DatePicker = viewBinding.datePickerNewSurvey


        //Setting TimePicker
        var timePicker: TimePicker = viewBinding.timePickerNewSurvey
        timePicker.setIs24HourView(false)

        /*
        variable beachSpinner to reference beachSpinner on the layout
         */

        val beachSpinner: Spinner = viewBinding.beachSpinner

        /*
        variable sectorSpinner to reference beachSpinner on the layout
         */
        val sectorSpinner: Spinner = viewBinding.sectorSpinner


        /*
        Setting arrayAdapter for beachSpinner
         */

        val beachAdapter = ArrayAdapter<String>(
            this.requireActivity(),
            android.R.layout.simple_spinner_item,
            beaches
        )

        /*
        Setting arrayAdapter for sectorSpinner
         */
        val sectorAdapter = ArrayAdapter<String>(
            this.requireActivity(),
            android.R.layout.simple_spinner_item,
            sector
        )

        //Setting drop down view resource to adapters
        beachAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)
        sectorAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)

        /*
        args variable would be null in case we access StartNewSurvey from Application home
        following normal UI sequence, but it will contain data if we are coming back to
        StartNewSurvey fragment from a fragment that comes later in the UI sequence
        */

        if (args.BeachDateTimeDataReturned != null) {

            //Setting Beach Spinner to previously selected option
            val beachSelected = args.BeachDateTimeDataReturned?.beach.toString()
            val indexOfBeach = beaches.indexOf(beachSelected)
            beachSpinner.post(Runnable { beachSpinner.setSelection(indexOfBeach) })

            //Setting Sector Spinner to previously selected option
            val sectorSelected = args.BeachDateTimeDataReturned?.sector.toString()
            val indexOfSector = sector.indexOf(sectorSelected)
            sectorSpinner.post(Runnable { sectorSpinner.setSelection(indexOfSector) })

            /*
            attaching adapters to spinners
             */
            beachSpinner.setAdapter(beachAdapter)
            sectorSpinner.setAdapter(sectorAdapter)

            /*Note: Not setting time and date that was previously selected on the screen as
             selections made before would cause unintentional wrong time / date values that were
             preselected for new survey
             */
        } else {
            /*
            attaching adapters to spinners without preference
             */
            beachSpinner.setAdapter(beachAdapter)
            sectorSpinner.setAdapter(sectorAdapter)
        }

        //Set onClick listener on Previous button
        viewBinding.buttonPrevious.setOnClickListener {
            view.findNavController()
                .navigate(StartNewSurveyDirections.actionStartNewSurveyToHomeScreen())
        }

        //Set onClick listener on Back arrow
        viewBinding.backArrow.setOnClickListener {
            view.findNavController()
                .navigate(StartNewSurveyDirections.actionStartNewSurveyToHomeScreen())
        }


        //Set onClick listener on Start a new morning survey plus image
        viewBinding.btnAddNewSurvey.setOnClickListener {

            //Setting beach and sector data on the viewModel
            startNewSurveyViewModel.beach = beachSpinner.selectedItem.toString()
            startNewSurveyViewModel.sector = sectorSpinner.selectedItem.toString()

            // Setting the selected date in viewModel
            startNewSurveyViewModel.dayOfTheMonth = datePicker.dayOfMonth

            // Setting the selected month in viewModel
            startNewSurveyViewModel.month = datePicker.month

            // Setting the selected year in viewModel
            startNewSurveyViewModel.year = datePicker.year

            // Setting the selected hour in viewModel
            startNewSurveyViewModel.hour = timePicker.hour

            // Setting the selected minute in viewModel
            startNewSurveyViewModel.minute = timePicker.minute


            //Setting data into the parcelable object to send it to next screen
            startNewSurveyViewModel.morningSurveyScreenOneData = MorningSurveyBeachDateTime(
                startNewSurveyViewModel.beach,
                startNewSurveyViewModel.sector,
                startNewSurveyViewModel.dayOfTheMonth,
                startNewSurveyViewModel.month,
                startNewSurveyViewModel.year,
                startNewSurveyViewModel.hour,
                startNewSurveyViewModel.minute,
                null
            )


            view.findNavController().navigate(
                StartNewSurveyDirections.actionStartNewSurveyToObserversWeather(
                    startNewSurveyViewModel.morningSurveyScreenOneData
                )
            )
        }

        return view
    }


}
