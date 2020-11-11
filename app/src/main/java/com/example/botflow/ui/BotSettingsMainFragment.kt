package com.example.botflow.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.drawerlayout.widget.DrawerLayout
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
        val drawerLayout = view.findViewById<DrawerLayout>(R.id.drawer_layout)
        val navigationView = view.findViewById<NavigationView>(R.id.navigationView)
        val header = navigationView.inflateHeaderView(R.layout.nav_header)
        val botNameTextView = header.findViewById<TextView>(R.id.botName_textView)
        botNameTextView.text = bot.name
        navigationView.bringToFront()
        updateUI(GeneralBotSettingsFragment(bot))
        navigationView.setNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.general -> {
                    updateUI(GeneralBotSettingsFragment(bot))
                    drawerLayout.closeDrawers()
                    true
                }
                R.id.intents -> {
                    updateUI(IntentsBotSettingsFragment(bot))
                    drawerLayout.closeDrawers()
                    true
                }
                R.id.entities -> {
                    updateUI(EntitiesBotSettingsFragment(bot))
                    drawerLayout.closeDrawers()
                    true
                }
                R.id.integrations -> {
                    updateUI(IntegrationsBotSettingsFragment(bot))
                    drawerLayout.closeDrawers()
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