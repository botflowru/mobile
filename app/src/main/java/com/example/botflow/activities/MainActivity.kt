package com.example.botflow.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.botflow.R
import com.example.botflow.fragments.AccountFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val email = intent.getStringExtra("email")!!
        val accountFragment = AccountFragment(email)
        updateUI(accountFragment)
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation_view)
        bottomNavigationView.setOnNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.bot_list -> {
                    true
                }
                R.id.account -> {
                    true
                }
                else -> false
            }

        }
    }
    private fun updateUI(view: Fragment){
        val fragmentManager = supportFragmentManager
        var fragment = fragmentManager.findFragmentById(R.id.fragment_container)
        if (fragment == null) {
            fragment = view
            fragmentManager.beginTransaction().apply {
                add(R.id.fragment_container, fragment)
            }.commit()
        }

    }
}