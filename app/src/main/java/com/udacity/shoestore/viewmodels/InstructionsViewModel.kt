package com.udacity.shoestore.viewmodels

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.udacity.shoestore.fragments.InstructionsFragmentDirections

class InstructionsViewModel : ViewModel() {

    fun getToShoesListScreen(v: View){
        // we always assume that input is correct, so this method simply navigates to the Onboarding Fragment
        v.findNavController().navigate(InstructionsFragmentDirections.actionInstructionsFragmentToShoesListFragment())
    }
}