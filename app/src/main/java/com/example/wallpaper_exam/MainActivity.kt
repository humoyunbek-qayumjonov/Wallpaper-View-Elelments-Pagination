package com.example.wallpaper_exam

import android.content.ContentValues.TAG
import android.content.Intent
import android.icu.text.Transliterator
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.*
import android.widget.FrameLayout
import androidx.navigation.findNavController
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.*

@Suppress("UNREACHABLE_CODE")
class MainActivity : AppCompatActivity(),ImageFragment.onSomeEventListener {

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)


        nav_view.itemIconTintList = null
        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(setOf(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow,R.id.nav_liked), drawer_layout)
        setupActionBarWithNavController(navController, appBarConfiguration)
        nav_view.setupWithNavController(navController)
        bottomBar.setupWithNavController(navController)

        bottomBar.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.nav_home->{
                    navController.navigate(R.id.nav_home)
                }
                R.id.nav_gallery->{
                    navController.navigate(R.id.nav_gallery)
                }
                R.id.nav_slideshow->{
                    navController.navigate(R.id.nav_slideshow)

                }
                R.id.nav_liked->{
                    navController.navigate(R.id.nav_liked)
                }
            }
            true
        }


        }


    private fun setBottom() {
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }


    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun someEvent(s: Int?) {
        val intent = Intent(this,SecondActivity::class.java)
        intent.putExtra("image1",s)
        startActivity(intent)
    }


}