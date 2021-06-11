package com.saliktariq.archelon.ui.login


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.saliktariq.archelon.databinding.LoginFragmentBinding
import com.saliktariq.archelon.helper.MyToasts
import kotlinx.android.synthetic.main.login_fragment.*
import kotlinx.coroutines.launch


class LoginFragment : Fragment() {

    private var binding: LoginFragmentBinding? = null
    private val viewBinding get() = binding!! //Ref: https://developer.android.com/topic/libraries/view-binding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //Getting reference to the binding object and inflating the fragment
        binding = LoginFragmentBinding.inflate(inflater, container, false)


        // Creating instance of ViewModel Factory
        val viewModelFactory = LoginViewModelFactory()

        // Get a reference to the ViewModel associated with LoginFragment
        val loginViewModel =
            ViewModelProvider(this, viewModelFactory).get(LoginViewModel::class.java)


        //Login button onClickListener to process login requests
        viewBinding.btnLogin.setOnClickListener {
            loginViewModel.uid = username.text.toString()
            loginViewModel.pwd = pwd.text.toString()
            viewLifecycleOwner.lifecycleScope.launch {
                val fetchData = viewLifecycleOwner.lifecycleScope.launch {
                    loginViewModel.login(loginViewModel.uid, loginViewModel.pwd)
                }
                //Fetch login data
                fetchData.join()
                if (loginViewModel.authenticated) {
                    viewModelStore.clear() //Clearing viewModel
                    viewBinding.root.findNavController()
                        .navigate(LoginFragmentDirections.actionLoginFragmentToHomeScreen())
                } else {
                    MyToasts.showToast("Invalid credentials!", context)
                    //Resetting the variables so they don't hold bad data (Strings)
                    loginViewModel.uid = ""
                    loginViewModel.pwd = ""
                }
            }


        }

        //Onclick listener for Signup button
        viewBinding.btnSignUp.setOnClickListener {
            val action = LoginFragmentDirections.actionLoginFragmentToRegisterNewUser()
            viewBinding.root.findNavController().navigate(action)

        }

        //Onclick listener for Forget password button
        viewBinding.btnForgetPassword.setOnClickListener {
            val action = LoginFragmentDirections.actionLoginFragmentToForgetPassword()
            viewBinding.root.findNavController().navigate(action)

        }
        return viewBinding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        //Destroying the view binding
        binding = null
    }

}
