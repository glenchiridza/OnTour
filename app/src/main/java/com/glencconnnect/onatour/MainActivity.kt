package com.glencconnnect.onatour

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.glencconnnect.onatour.R
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.navigation.NavigationView


class MainActivity : AppCompatActivity() {

    private lateinit var toolbar        : MaterialToolbar
    private lateinit var navController  : NavController
    private lateinit var navigationView : NavigationView
    private lateinit var draweLayout    : DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize Views
        toolbar         = findViewById(R.id.activity_main_toolbar)
        navigationView         = findViewById(R.id.nav_view)
        draweLayout         = findViewById(R.id.drawer_layout)

        // Get NavHostFragment and NavController
        val navHostFrag = supportFragmentManager.findFragmentById(R.id.nav_host_frag) as NavHostFragment
        navController   = navHostFrag.navController

        //app bar cconfig connet drawelayout wit nav component
        val appBarConfiguration = AppBarConfiguration(navController.graph, draweLayout)

        // connect toolbar with navigation controller
        toolbar.setupWithNavController(navController,appBarConfiguration)

        //connect navigation view with nav controller
        navigationView.setupWithNavController(navController)

    }

    override fun onBackPressed() {
        if(draweLayout.isOpen){
            draweLayout.close()
        }else {
            super.onBackPressed()
        }
    }
}