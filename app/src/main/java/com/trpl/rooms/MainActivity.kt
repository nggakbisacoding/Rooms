package com.trpl.rooms

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import android.view.Menu
import androidx.fragment.app.Fragment
import com.trpl.rooms.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dataintent = intent
        val pos = dataintent.getStringExtra("pos")

        binding.toolbar.setOnClickListener {
            when(it.id) {
                R.id.nav_home -> replaceFragment(FirstFragment(), pos)
                R.id.nav_profile -> replaceFragment(SecondFragment(), pos)
            }
        }
        binding.fab.setOnClickListener { _ ->
            val intents = Intent(this, CreateUser::class.java)
            startActivity(intents)
            finish()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.navbar, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }

    private fun replaceFragment(fragment: Fragment, usn: String? = null) {
        val b = Bundle()
        b.putInt("pos", usn!!.toInt())
        fragment.arguments = b
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.nav_host_fragment_content_main, fragment)
        fragmentTransaction.commit()
    }
}