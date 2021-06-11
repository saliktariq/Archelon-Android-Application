package com.saliktariq.archelon.ui.firstscreen


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.saliktariq.archelon.databinding.HomeScreenFragmentBinding
import com.saliktariq.archelon.helper.AppContext
import com.saliktariq.archelon.helper.MyToasts


class ApplicationHomeScreenFragment : Fragment(), View.OnClickListener {

    private var binding: HomeScreenFragmentBinding? = null
    private val viewBinding get() = binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = HomeScreenFragmentBinding.inflate(
            inflater, container, false
        )
        val view = viewBinding.root

        //Set onclick listener for Logout button
        viewBinding.btnLogOut.setOnClickListener {
            viewBinding.root.findNavController()
                .navigate(ApplicationHomeScreenFragmentDirections.actionGlobalLoginFragment())
        }

        //Set onClick listener for Start New Survey button
        viewBinding.btnStartNewSurvey.setOnClickListener {
            viewBinding.root.findNavController().navigate(
                (ApplicationHomeScreenFragmentDirections.actionHomeScreenToStartNewSurvey(null))
            )
        }

        //Set onclick listener on unimplemented buttons
        viewBinding.btnNestCheck.setOnClickListener(this)
        viewBinding.btnEmergencyRelocation.setOnClickListener(this)
        viewBinding.btnStartExcavation.setOnClickListener(this)

        //Returning view
        return view
    }


    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    override fun onClick(v: View?) {
        //This message is to be replaced by actual implementation of buttons i.e., navigation
        MyToasts.showToast("Feature not implemented", AppContext.appContext)
    }


}
