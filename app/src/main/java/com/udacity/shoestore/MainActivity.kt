package com.udacity.shoestore


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.databinding.ActivityMainBinding
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        Timber.plant(Timber.DebugTree())

        navController = this.findNavController(R.id.navHostFragment)
        setSupportActionBar(binding.toolbar)

        // we define as top level destinations the login, welcome, instructions, shoesList fragments
        // so they don't contain a back button, as the instructions state "the login and onboarding pages do not show again"
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.loginFragment,
                R.id.welcomeFragment,
                R.id.instructionsFragment,
                R.id.shoesListFragment
            )
        )

        // create the ActionBar managed by the NavigationUI, and pass as a 3rd param the "appBarConfiguration"
        // so it hides the back button on the login and onbording (welcome) screens.
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration)
    }

    override fun onSupportNavigateUp(): Boolean {
        this.onBackPressed()
        return super.onSupportNavigateUp()
    }
}
