package com.udacity.shoestore.viewmodels

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.udacity.shoestore.fragments.ShoesListFragmentDirections
import com.udacity.shoestore.models.Shoe

class ShoesListViewModel: ViewModel() {

    fun openShoeDetailsNew(view: View){
        view.findNavController().navigate(ShoesListFragmentDirections.actionShoesListFragmentToShoeDetailFragment(null))
    }

    fun openShoeDetailsEdit(view: View, shoe: Shoe? = null){
        view.findNavController().navigate(ShoesListFragmentDirections.actionShoesListFragmentToShoeDetailFragment(shoe))
    }
}