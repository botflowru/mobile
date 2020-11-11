package com.example.botflow.ui

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

class ErrorDialog : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity!!.let {
            val builder = AlertDialog.Builder(it)
            builder.setTitle("Network error")
                    .setPositiveButton("OK") { dialog, _ ->
                        activity!!.finishAffinity()
                        dialog.cancel()
                    }
            builder.create()
        }
    }
}