package com.example.botflow.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.botflow.R
import com.example.botflow.models.Bot

class EntitiesBotSettingsFragment(bot: Bot) : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.entities_settings, container, false)
    }
}