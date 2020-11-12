package com.example.botflow.activities

import androidx.fragment.app.Fragment
import com.example.botflow.ui.MainFragment

class MainActivity : SingleFragmentActivity(){
    override fun createFragment(): Fragment {
        return MainFragment()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        this.finishAffinity()
    }

}