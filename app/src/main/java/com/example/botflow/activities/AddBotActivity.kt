package com.example.botflow.activities

import androidx.fragment.app.Fragment
import com.example.botflow.ui.AddBotFragment

class AddBotActivity : SingleFragmentActivity() {
    override fun createFragment(): Fragment {
        return AddBotFragment()
    }
}