package com.example.botflow.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.botflow.R
import com.example.botflow.models.Bot
import com.google.android.material.navigation.NavigationView

class BotSettingsMainFragment : Fragment() {
    private lateinit var bot: Bot

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        bot = activity!!.intent.getSerializableExtra("bot") as Bot
        return inflater.inflate(R.layout.bot_settings_main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navigationView = view.findViewById<NavigationView>(R.id.navigationView)
        val header = navigationView.inflateHeaderView(R.layout.nav_header)
        val botNameTextView = header.findViewById<TextView>(R.id.botName_textView)
        botNameTextView.text = bot.name
        navigationView.bringToFront()
        navigationView.setNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.general -> {
                    Toast.makeText(context, "click", Toast.LENGTH_SHORT).show()
                    updateUI(GeneralBotSettingsFragment(bot))
                    true
                }
                R.id.intents -> {
                    updateUI(IntentsBotSettingsFragment(bot))
                    true
                }
                R.id.entities -> {
                    updateUI(EntitiesBotSettingsFragment(bot))
                    true
                }
                R.id.integrations -> {
                    updateUI(IntegrationsBotSettingsFragment(bot))
                    true
                }
                else -> false
            }
        }
    }
    private fun updateUI(view: Fragment){
        val fragmentManager = activity!!.supportFragmentManager
        fragmentManager.beginTransaction().apply {
            replace(R.id.bot_settings_fragment_container, view)
        }.commit()
    }
}