package com.udacity.shoestore.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeDetailBinding
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.viewmodels.SharedViewModel
import com.udacity.shoestore.viewmodels.ShoeDetailViewModel
import com.udacity.shoestore.viewmodels.ShoeDetailViewModelFactory
import java.nio.channels.Selector

class ShoeDetailFragment : Fragment() {
    private lateinit var binding: FragmentShoeDetailBinding
    private lateinit var args: ShoeDetailFragmentArgs
    private var isNewItem = true

    private lateinit var viewModel: ShoeDetailViewModel
    private lateinit var viewModelFactory: ShoeDetailViewModelFactory

    private val sharedViewModel: SharedViewModel by activityViewModels()
    private lateinit var itemSelector: Selector

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_shoe_detail, container, false
        )

        args = ShoeDetailFragmentArgs.fromBundle(requireArguments())

        // Fragment Level ViewModel Assignment
        var displayedShoe: Shoe? = null
        if (args.shoe == null)
            displayedShoe = Shoe("", 0.0, "", "", mutableListOf("Mens Shoes"))
        else
            displayedShoe = args.shoe

        viewModelFactory = ShoeDetailViewModelFactory(displayedShoe!!, (args.shoe==null))
        viewModel = ViewModelProvider(this, viewModelFactory).get(ShoeDetailViewModel::class.java)

        // Associate the ViewModel to the xml data variable named "viewModel"
        binding.viewModel = viewModel

        // Activity Level ViewModel Assignment
        sharedViewModel.shoesList.observe(viewLifecycleOwner, Observer {

        })

        // get Action Bar title String Rersource Id, and display it
        viewModel.actionBarTitleId.observe(viewLifecycleOwner, Observer { resourceStringId ->
            (activity as AppCompatActivity).supportActionBar?.title = getString(resourceStringId)
        })

        // if commit changes gets to true, we either update or insert item
        viewModel.commitChanges.observe(viewLifecycleOwner, Observer {
            var shoeSize: Double
            try {shoeSize = viewModel.shoeSize.value?.toDouble() ?: 0.0} catch(e: NumberFormatException) { shoeSize = 0.0}

            displayedShoe?.apply {
                name = viewModel.shoeName.value.toString()
                size = shoeSize
                company = viewModel.shoeCompany.value.toString()
                description = viewModel.shoeDescription.value.toString()
                images[0] = binding.shoeTypesSpinner.selectedItem.toString()
            }

            if (viewModel.isNewItem){
                sharedViewModel.shoesList.value?.add(displayedShoe)
            }
        })

        viewModel.alertDialogEvent.observe(viewLifecycleOwner, Observer {showErrorDialog ->
            if (showErrorDialog){
                val builder = AlertDialog.Builder(context)
                builder.setTitle(getString(R.string.incomplete_fields_error_title))
                builder.setMessage(getString(R.string.incomplete_fields_error_msg))
                builder.setPositiveButton(android.R.string.ok) { dialog, which ->
                    viewModel.onAlertDialogShowed()
                }
                builder.show()
            }
        })

        // set the spinner to display the correct type of shoe, so it is resembled as image correctly
        viewModel.shoeImageIdx.observe(viewLifecycleOwner, Observer {
            binding.shoeTypesSpinner.setSelection(it?:2)
        })

        return binding.root
    }

}