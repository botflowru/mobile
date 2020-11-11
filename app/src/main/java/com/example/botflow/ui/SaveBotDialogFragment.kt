package com.example.botflow.ui

import android.app.Dialog
import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.botflow.R

class SaveBotDialogFragment(private val viewModel: MainViewModel) : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialogView = activity!!.layoutInflater.inflate(R.layout.add_bot_dialog, null)
        val botNameEditText = dialogView.findViewById<EditText>(R.id.botName_editText)
        return activity!!.let {
            val builder = AlertDialog.Builder(it)
            builder.setTitle("Add new bot")
                .setView(dialogView)
                .setPositiveButton("OK") { dialog, _ ->
                    val name = botNameEditText.text.toString()
                    viewModel.saveBot(name)
                    dialog.cancel()
                }
            builder.create()
        }
    }
}