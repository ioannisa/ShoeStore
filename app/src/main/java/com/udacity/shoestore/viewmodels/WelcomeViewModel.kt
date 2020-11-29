package com.udacity.shoestore.viewmodels

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.udacity.shoestore.fragments.WelcomeFragmentDirections

class WelcomeViewModel: ViewModel() {

    fun getToInstructionsScreen(v: View) {
        // we always assume that input is correct, so this method simply navigates to the Onboarding Fragment
        v.findNavController()
            .navigate(WelcomeFragmentDirections.actionWelcomeFragmentToInstructionsFragment())
    }
}