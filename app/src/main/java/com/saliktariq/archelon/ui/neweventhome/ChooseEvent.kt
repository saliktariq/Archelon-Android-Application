package com.saliktariq.archelon.ui.neweventhome

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.saliktariq.archelon.databinding.ChooseEventFragmentBinding
import com.saliktariq.archelon.datalayer.parcelables.MorningSurveyWithObserversWeather
import com.saliktariq.archelon.helper.AppContext
import com.saliktariq.archelon.helper.MyToasts


class ChooseEvent : Fragment(), View.OnClickListener {

    private var binding: ChooseEventFragmentBinding? = null
    private val viewBinding get() = binding!!

    //Variable to contain received Args object
    private val args by navArgs<ChooseEventArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //Getting reference to the binding object and inflating the fragment
        binding = ChooseEventFragmentBinding.inflate(inflater, container, false)

        // Creating instance of ViewModel Factory
        val viewModelFactory = ChooseEventViewModelFactory()

        // Get a reference to the ViewModel associated with SurveyHomeScreen Fragment
        val chooseEventViewModel =
            ViewModelProvider(this, viewModelFactory).get(ChooseEventViewModel::class.java)

        // Assigning received args to viewModel variable
        chooseEventViewModel.morningSurveyScreenOneAndScreenTwoData = args.MorningSurveyData


        //Set onClick Listener on Previous button
        viewBinding.buttonPrevious.setOnClickListener {
            viewBinding.root.findNavController().navigate(
                ChooseEventDirections.actionChooseEventToSurveyHomeScreen(chooseEventViewModel.morningSurveyScreenOneAndScreenTwoData)
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
                    .navigate(ChooseEventDirections.actionChooseEventToHomeScreen())
            }

            cancelSurveyDialog.setNegativeButton(
                "No"
            ) { dialog, _ -> dialog.cancel() }

            val createDialogue = cancelSurveyDialog.create()
            createDialogue.show()

        }

        //Set onclick listener on unimplemented buttons
        viewBinding.btnAdultEmergence.setOnClickListener(this)
        viewBinding.btnHatching.setOnClickListener(this)
        viewBinding.btnFoundByHatching.setOnClickListener(this)
        viewBinding.btnInundationWash.setOnClickListener(this)
        viewBinding.btnAttemptedPredation.setOnClickListener(this)
        viewBinding.btnFoundByPredation.setOnClickListener(this)
        viewBinding.btnFoundByOther.setOnClickListener(this)
        viewBinding.btnVandalism.setOnClickListener(this)
        viewBinding.btnOtherEvent.setOnClickListener(this)

        return viewBinding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        //Destroying the view binding
        binding = null
    }

    override fun onClick(v: View?) {
        //This code is to be replaced by actual implementation of buttons i.e., navigation
        MyToasts.showToast("Feature not implemented", AppContext.appContext)
    }
}
