package com.udacity.shoestore.viewmodels

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.models.Shoe

class ShoeDetailViewModel(var shoe: Shoe?, var isNewItem: Boolean): ViewModel() {

    var alertDialogCount = MutableLiveData<Int>(0)
    var commitChanges = MutableLiveData<Boolean>()

    var shoeName = MutableLiveData<String>("")
    var shoeCompany = MutableLiveData<String>("")
    var shoeDescription = MutableLiveData<String>("")
    var shoeSize = MutableLiveData<String>("")
    var shoeImageIdx = MutableLiveData<Int>()

    var actionBarTitleId: MutableLiveData<Int> = MutableLiveData()

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
        if ((shoeName.value?.trim()=="") ||
            (shoeCompany.value?.trim()=="") ||
            (shoeDescription.value?.trim()=="") ||
            (shoeSize.value?.trim()=="")
        ){
            alertDialogCount.value = alertDialogCount.value?.plus(1)
        }
        else {
            commitChanges.value = true
            navigateUp(v)
        }
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
            shoeSize.value = shoe?.size.toString() ?: "0.0"

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