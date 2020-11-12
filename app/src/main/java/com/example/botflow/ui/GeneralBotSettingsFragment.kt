package com.example.botflow.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.example.botflow.R
import com.example.botflow.models.Bot

class GeneralBotSettingsFragment(private val bot: Bot, private val viewModel: MainViewModel) : Fragment()  {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.general_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val editText = view.findViewById<EditText>(R.id.general_editText)
        val saveButton = view.findViewById<Button>(R.id.general_save_button)
        saveButton.setOnClickListener {
            val name = editText.text.toString()
            viewModel.updateBot(bot.id!!, name)
        }
        editText.setText(bot.name)
    }
}