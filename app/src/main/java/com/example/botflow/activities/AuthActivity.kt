package com.example.botflow.activities

import androidx.fragment.app.Fragment
import com.example.botflow.ui.AuthFragment

class AuthActivity : SingleFragmentActivity() {
    override fun createFragment(): Fragment {
        return AuthFragment()
    }

}