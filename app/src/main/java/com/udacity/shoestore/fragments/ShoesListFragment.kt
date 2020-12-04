package com.udacity.shoestore.fragments

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.NavigationDirections
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoesListBinding
import com.udacity.shoestore.databinding.ItemRowBinding
import com.udacity.shoestore.viewmodels.SharedViewModel
import com.udacity.shoestore.viewmodels.ShoesListViewModel


class ShoesListFragment : Fragment() {

    private lateinit var binding: FragmentShoesListBinding
    private lateinit var viewModel: ShoesListViewModel

    // SharedViewModel Assignment
    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        // Data Binding
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_shoes_list, container, false
        )

        // ViewModel Assignment
        viewModel = ViewModelProvider(this).get(ShoesListViewModel::class.java)

        // Associate the ViewModel to the xml data variable named "viewModel"
        binding.viewModel = viewModel

        // Inflate Item Rows to the LinearLayout from the SharedViewModel
        sharedViewModel.shoesList.observe(viewLifecycleOwner, { shoesList ->
            for (shoe in shoesList){
                // inflate a new shoe row
                val view = DataBindingUtil.inflate<ItemRowBinding>(layoutInflater, R.layout.item_row, binding.shoesList,true).apply {
                    this.shoe = shoe

                    val shoeImgDescr = if (shoe.images.isEmpty())  "" else shoe.images[0]

                    // fetch the first of the drawables list of the item to be the representative image
                    val drawableId = sharedViewModel.getDrawableIdByName(shoeImgDescr)
                    shoeThumb.setImageDrawable(ContextCompat.getDrawable((activity as AppCompatActivity),  drawableId))
                }

                // and set click listener t
                view.root.setOnClickListener{
                    binding.viewModel?.openShoeDetailsEdit(it, shoe)
                }
            }
        })

        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.overflow_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.logout_menu_item -> {
                // take global action that navigates to login screen and pops everything off the backstack
                findNavController().navigate(NavigationDirections.actionGlobalLoginFragment())
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}