package com.udacity.shoestore.viewmodels

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.models.Shoe

class ShoeDetailViewModel(var shoe: Shoe?, var isNewItem: Boolean): ViewModel() {

    private val _alertDialogEvent = MutableLiveData<Boolean>(false)
    val alertDialogEvent: LiveData<Boolean>
        get() = _alertDialogEvent


    val commitChanges = MutableLiveData<Boolean>()

    val shoeName = MutableLiveData<String>()
    val shoeCompany = MutableLiveData<String>()
    val shoeDescription = MutableLiveData<String>()
    val shoeSize = MutableLiveData<String>()
    val shoeImageIdx = MutableLiveData<Int>()

    val actionBarTitleId: MutableLiveData<Int> = MutableLiveData()

    private val imageTypes = arrayListOf<String>(
        "Men Shoes",
        "Sneakers",
        "High Heels",
        "Boots",
        "Cleats"
    )

    init{
        populateControls()
    }



    fun navigateUp(v: View){
        v.findNavController().navigateUp()
    }

    fun commitChanges(v: View){
        if ((shoeName.value?.trim()=="" || shoeName.value == null) ||
            (shoeCompany.value?.trim()==""  || shoeCompany.value == null) ||
            (shoeDescription.value?.trim()=="" || shoeDescription.value == null) ||
            (shoeSize.value?.trim()=="" || shoeSize.value == null)
        ){
            _alertDialogEvent.value = true
        }
        else {
            commitChanges.value = true
            navigateUp(v)
        }
    }

    fun onAlertDialogShowed(){
        _alertDialogEvent.value = false
    }

    private fun populateControls(){
        if (isNewItem) {
            // ADDING NEW SHOE
            actionBarTitleId.value =  R.string.action_bar_new_shoe_msg
            shoeImageIdx.value = 0
        }
        else {

            actionBarTitleId.value =  R.string.action_bar_new_shoe_msg

            shoeName.value = shoe?.name ?: ""
            shoeCompany.value = shoe?.company ?: ""
            shoeDescription.value = shoe?.description ?: ""
            shoeSize.value = shoe?.size.toString()

            // the image
            val baseImageDescription = shoe?.images?.get(0) ?: "Mens Shoes"
            if (shoe?.images?.isNotEmpty() == true){
                for ((i, imageDescr) in imageTypes.withIndex()) {
                    if (baseImageDescription == imageDescr){
                        shoeImageIdx.value = i
                        break
                    }
                }
            }
            else{
                shoeImageIdx.value = 0
            }
        }
    }
}