package com.example.botflow.activities

import androidx.fragment.app.Fragment
import com.example.botflow.ui.BotSettingsMainFragment

class BotSettingsActivity : SingleFragmentActivity() {
    override fun createFragment(): Fragment {
        return BotSettingsMainFragment()
    }
}