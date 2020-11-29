package com.udacity.shoestore.fragments

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentInstructionsBinding
import com.udacity.shoestore.viewmodels.InstructionsViewModel

class InstructionsFragment : Fragment() {

    private lateinit var binding: FragmentInstructionsBinding
    private lateinit var viewModel: InstructionsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Data  Binding
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_instructions, container, false
        )

        // ViewModel Assignment
        viewModel = ViewModelProvider(this).get(InstructionsViewModel::class.java)
        // Associate the ViewModel to the xml data variable named "viewModel"
        binding.viewModel = viewModel

        return binding.root
    }
}