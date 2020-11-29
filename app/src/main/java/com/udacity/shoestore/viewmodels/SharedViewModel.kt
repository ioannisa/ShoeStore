package com.udacity.shoestore.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.R
import com.udacity.shoestore.models.Shoe

/**
 * Shared View Model, meant to be used in the Master/Detail model of
 * List of Shoes + Shoe Detail
 */
class SharedViewModel: ViewModel() {

    private var shoeDrawables: HashMap<String, Int> = HashMap<String, Int>()
    private var _shoesList = MutableLiveData<ArrayList<Shoe>>()

    val shoesList: LiveData<ArrayList<Shoe>>
        get() = _shoesList

    init{
        _shoesList.value = ArrayList<Shoe>()
        createDrawablesHashMap()
        populateWithDummyData()
    }

    /**
     * Gererate a HashMap<String,Int> descriptions of Image Drawables for our shoes
     */
    private fun createDrawablesHashMap(){
        // default drawable images
        with (shoeDrawables){
            this["Men Shoes"] = R.drawable.mens;
            this["Sneakers"] = R.drawable.sport_shoes
            this["High Heels"] = R.drawable.high_heels
            this["Boots"] = R.drawable.mukluks
            this["Cleats"] = R.drawable.cleats
        }
    }

    /**
     * Populate the ArrayList<Shoe> with seven dummy entries
     */
    private fun populateWithDummyData(){
        _shoesList.value?.apply {
            add(Shoe("Casual", 7.0, "New Balance", "Brown Leather", mutableListOf("Men Shoes")))
            add(Shoe("Sports", 8.0, "Puma", "Running Shoes", mutableListOf("Sneakers")))
            add(Shoe("Fem Boots", 6.0, "Tommy Hilfiger", "Woman's Luxury Boots", mutableListOf("Boots")))
            add(Shoe("Soccer Shoes", 7.5, "Kappa", "Ajax Team Shoes", mutableListOf("Cleats")))
            add(Shoe("Fem Dining Shoes", 7.5, "Gucci", "High heels dining shoes", mutableListOf("High Heels")))
            add(Shoe("LunarGlide 6", 7.5, "Nike", "AirSole Runner Shoes", mutableListOf("Sneakers")))
            add(Shoe("Enforced Soccer Shoes", 7.0, "Adidas", "Barcelona Team Shoes", mutableListOf("Cleats")))
        }
    }

    /**
     * Return the Drawable ID of the first image of the image list
     * Default ID: R.drawable.mens
     */
    fun getDrawableIdByName(key: String): Int{
        return shoeDrawables[key] ?: R.drawable.mens
    }
}