package com.udacity.shoestore.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentLoginBinding
import com.udacity.shoestore.viewmodels.LoginViewModel


class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {

        // Data Binding
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_login, container, false
        )

        // ViewModel Assignment
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        // Associate the ViewModel to the xml data variable named "viewModel"
        binding.viewModel = viewModel

        return binding.root
    }
}