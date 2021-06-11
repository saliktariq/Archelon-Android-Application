package com.saliktariq.archelon.ui.login

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.saliktariq.archelon.databinding.RegisterNewUserFragmentBinding
import com.saliktariq.archelon.helper.AuxiliaryFunctions
import com.saliktariq.archelon.helper.MyToasts
import kotlinx.android.synthetic.main.register_new_user_fragment.*
import kotlinx.coroutines.launch

class RegisterNewUser : Fragment() {
    private var binding: RegisterNewUserFragmentBinding? = null
    private val viewBinding get() = binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //Getting reference to the binding object and inflating the fragment
        binding = RegisterNewUserFragmentBinding.inflate(inflater, container, false)

        // Creating instance of ViewModel Factory
        val viewModelFactory = RegisterNewUserViewModelFactory()

        // Get a reference to the ViewModel associated with RegisterNewUser fragment
        val registerNewUserViewModel =
            ViewModelProvider(this, viewModelFactory).get(RegisterNewUserViewModel::class.java)

        // Set onClickListener on the back button
        viewBinding.backArrowRegPage.setOnClickListener {
            viewBinding.root.findNavController()
                .navigate(RegisterNewUserDirections.actionGlobalLoginFragment())
        }

        // Set onClickListener on the login link
        viewBinding.loginLinkRegPage.setOnClickListener {
            viewBinding.root.findNavController()
                .navigate(RegisterNewUserDirections.actionGlobalLoginFragment())
        }

        // Set onClickListener to Signup button
        viewBinding.btnSignUp.setOnClickListener {

            //Assigning user entered data to view model variables
            registerNewUserViewModel.firstName = firstName.text.toString()
            registerNewUserViewModel.email = emailAddress.text.toString()
            registerNewUserViewModel.username = newUsername.text.toString()
            registerNewUserViewModel.pwd = password.text.toString()

            //Generating new authentication code
            registerNewUserViewModel.authCode = AuxiliaryFunctions.generateAuthCode(
                registerNewUserViewModel.username,
                registerNewUserViewModel.pwd,
                registerNewUserViewModel.email,
                registerNewUserViewModel.firstName
            )

            //Starting coroutine with application lifecycle scope
            viewLifecycleOwner.lifecycleScope.launch {
                //Checking if the registration data entered is not already in the system
                val fetchData = viewLifecycleOwner.lifecycleScope.launch {
                    registerNewUserViewModel.getUserData(registerNewUserViewModel.username)
                }
                //Query database to see if the dataset (username or email address) already exists
                //Waiting for the coroutine to complete query
                fetchData.join()


                //Checking if email already exist in the system
                if (registerNewUserViewModel.emailAlreadyExists) {
                    MyToasts.showToastWithGravity(
                        "Email address already exist.",
                        context,
                        Gravity.TOP
                    )
                }
                //Checking if email field is empty or inval
                else if (registerNewUserViewModel.email.length < 3) {
                    val toastHandler = Handler()
                    toastHandler.postDelayed({
                        MyToasts.showToastWithGravity(
                            "Enter a valid email address",
                            context,
                            Gravity.TOP
                        )
                    }, 2000)
                }

                //Checking if username field is empty or invalid
                else if (registerNewUserViewModel.username.length < 5) {
                    val toastHandler = Handler()
                    toastHandler.postDelayed({
                        MyToasts.showToastWithGravity(
                            "Minimum username length is 5 characters",
                            context,
                            Gravity.TOP
                        )
                    }, 2000)
                } else if (registerNewUserViewModel.userAlreadyExists) {
                    /*Using handler to display second toast right after the first error toast
                    to avoid second toast override first toast message.
                     */
                    val toastHandler = Handler()
                    toastHandler.postDelayed({
                        MyToasts.showToastWithGravity(
                            "Username already exist.",
                            context,
                            Gravity.TOP
                        )
                    }, 2000) //Adding delay to avoid toasts overlapping in case of multiple errors
                }

                //Checking if password field is 5 characters or more
                else if (registerNewUserViewModel.pwd.length < 5) {
                    val toastHandler = Handler()
                    toastHandler.postDelayed({
                        MyToasts.showToastWithGravity(
                            "Minimum password length is 5 characters",
                            context,
                            Gravity.BOTTOM
                        )
                    }, 2000) //Adding delay to avoid toasts overlapping in case of multiple errors
                } else {
                    val signup = viewLifecycleOwner.lifecycleScope.launch {
                        registerNewUserViewModel.signUpUser()
                    }
                    //Waiting for signup query to complete
                    signup.join()
                    MyToasts.showToastWithGravity(
                        "Success! User account created",
                        context,
                        Gravity.TOP
                    )

                    viewModelStore.clear() //Clearing viewModel
                    viewBinding.root.findNavController()
                        .navigate(RegisterNewUserDirections.actionGlobalLoginFragment())
                }
                registerNewUserViewModel.emailAlreadyExists = false
                registerNewUserViewModel.userAlreadyExists = false
            }

        }

        //Returning view
        return viewBinding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        //Destroying the viewBinding object
        binding = null
    }
}

