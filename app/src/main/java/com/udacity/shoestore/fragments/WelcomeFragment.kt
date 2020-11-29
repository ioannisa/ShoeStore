package com.udacity.shoestore.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentWelcomeBinding
import com.udacity.shoestore.viewmodels.WelcomeViewModel

class WelcomeFragment : Fragment() {

    private lateinit var binding: FragmentWelcomeBinding
    private lateinit var viewModel: WelcomeViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        // Data Binding
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_welcome, container, false
        )

        // ViewModel Assignment
        viewModel = ViewModelProvider(this).get(WelcomeViewModel::class.java)
        // Associate the ViewModel to the xml data variable named "viewModel"
        binding.viewModel = viewModel

        return binding.root
    }
}