package com.saliktariq.archelon.ui.login

import android.os.Bundle
import android.os.Handler
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.saliktariq.archelon.databinding.NewPasswordFragmentBinding
import com.saliktariq.archelon.helper.MyToasts
import kotlinx.android.synthetic.main.new_password_fragment.*
import kotlinx.coroutines.launch


class NewPassword : Fragment() {
    private var binding: NewPasswordFragmentBinding? = null
    private val viewBinding get() = binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //Getting reference to the binding object and inflating the fragment
        binding = NewPasswordFragmentBinding.inflate(inflater, container, false)

        // Creating instance of ViewModel Factory
        val viewModelFactory = NewPasswordViewModelFactory()

        // Get a reference to the ViewModel associated with NewPassword fragment
        val newPasswordViewModel =
            ViewModelProvider(this, viewModelFactory).get(NewPasswordViewModel::class.java)

        // Set onClickListener on the back button
        viewBinding.backArrowNewPasswordPage.setOnClickListener {
            viewBinding.root.findNavController()
                .navigate(NewPasswordDirections.actionGlobalLoginFragment())
        }

        //Set onClickListener on the Signup button
        viewBinding.btnSignup.setOnClickListener {
            viewBinding.root.findNavController()
                .navigate(NewPasswordDirections.actionNewPasswordToRegisterNewUser())
        }

        //Set onClickListener on Reset Password button
        viewBinding.btnResetPassword.setOnClickListener {

            //Setting the viewModel variables
            newPasswordViewModel.emailAddress = emailAddress.text.toString()
            newPasswordViewModel.authCode = (authCode.text.toString()).toLongOrNull()
            newPasswordViewModel.newPassword = password.text.toString()
            newPasswordViewModel.retypeNewPassword = retypeNewPassword.text.toString()


            //Checking if matching password and retype password are entered ahead of running a coroutine
            if (!(newPasswordViewModel.passwordEqualsRetypePassword())) {
                MyToasts.showToast("Password and retype Password must be equal", context)
            } else {
                viewLifecycleOwner.lifecycleScope.launch {
                    //Assigning getUserData to variable fetchData
                    val fetchData = viewLifecycleOwner.lifecycleScope.launch {
                        newPasswordViewModel.getUserData()
                    }
                    //Fetch data and update variable isValidEmail before checking this variable in condition
                    fetchData.join()
                    if (!newPasswordViewModel.emailPresentInDataSource()) {
                        MyToasts.showToastWithGravity(
                            "Email address is incorrect.",
                            context,
                            Gravity.BOTTOM
                        )
                    }


                    if (!(newPasswordViewModel.authCodePresentInDataSource())) {
                        /*Using handler to display second toast right after the email error toast
                        to avoid second toast override first toast message
                         */
                        val toastHandler = Handler()
                        toastHandler.postDelayed(
                            {
                                MyToasts.showToastWithGravity(
                                    "Authorization code is incorrect.",
                                    context,
                                    Gravity.BOTTOM
                                )
                            },
                            2000
                        ) //Delay is added to avoid toasts overlapping in case of multiple errors
                    } else {
                        val changePassword = viewLifecycleOwner.lifecycleScope.launch {
                            newPasswordViewModel.updatePasswordAndAuthCode()
                        }
                        changePassword.join()
                        if (newPasswordViewModel.isOperationCompleted) {
                            MyToasts.showToastWithGravity(
                                "Your password is reset successfully!",
                                context, Gravity.TOP
                            )
                            viewBinding.root.findNavController()
                                .navigate(NewPasswordDirections.actionGlobalLoginFragment())
                        } else {
                            MyToasts.showToastWithGravity(
                                "Error! Operation unsuccessful.",
                                context, Gravity.TOP
                            )
                        }

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
