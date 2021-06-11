package com.saliktariq.archelon.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.saliktariq.archelon.databinding.ForgetPasswordFragmentBinding
import com.saliktariq.archelon.helper.MyToasts
import kotlinx.android.synthetic.main.forget_password_fragment.*
import kotlinx.coroutines.launch


class ForgetPassword : Fragment() {
    private var binding: ForgetPasswordFragmentBinding? = null
    private val viewBinding get() = binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //Getting reference to the binding object and inflating the fragment
        binding = ForgetPasswordFragmentBinding.inflate(inflater, container, false)

        // Creating instance of ViewModel Factory
        val viewModelFactory = ForgetPasswordViewModelFactory()

        // Get a reference to the ViewModel associated with RegisterNewUser fragment
        val forgetPasswordViewModel =
            ViewModelProvider(this, viewModelFactory).get(ForgetPasswordViewModel::class.java)

        // Set onClickListener on the back button
        viewBinding.backArrowForgetPage.setOnClickListener {
            viewBinding.root.findNavController()
                .navigate(ForgetPasswordDirections.actionGlobalLoginFragment())
        }

        // Set onClickListener on Signup button
        viewBinding.btnSignUp.setOnClickListener {
            viewBinding.root.findNavController()
                .navigate(ForgetPasswordDirections.actionForgetPasswordToRegisterNewUser())
        }

        // Set onClickListener on Enter reset code button
        viewBinding.btnEnterCode.setOnClickListener {
            viewBinding.root.findNavController()
                .navigate(ForgetPasswordDirections.actionForgetPasswordToNewPassword())
        }

        // Set onClickListener to btnRequestPassword button
        viewBinding.btnRequestPassword.setOnClickListener {
            forgetPasswordViewModel.emailAddress = emailAddress.text.toString()
            if (forgetPasswordViewModel.emailAddress.length < 3) { //The theoretical minimum length of any email address is 3
                MyToasts.showToast("Enter valid email address", context)
            } else {
                viewLifecycleOwner.lifecycleScope.launch {
                    val fetchData = viewLifecycleOwner.lifecycleScope.launch {
                        forgetPasswordViewModel.sendAuthCode()
                    }
                    //Fetch data and update variable isValidEmail before checking this variable in condition
                    fetchData.join()

                    if (forgetPasswordViewModel.isValidEmail) {
                        MyToasts.showToast(
                            "You password reset code is sent to your registered email address.",
                            context
                        )
                        viewModelStore.clear() //Clearing viewModel to avoid data leak to subsequent visit to fragment
                        viewBinding.root.findNavController()
                            .navigate(ForgetPasswordDirections.actionForgetPasswordToNewPassword())
                    } else {
                        MyToasts.showToast("Email address is not registered.", context)
                    }

                }
            }


        }
        //Returning view
        return viewBinding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}