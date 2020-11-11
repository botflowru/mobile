package com.example.botflow.ui

import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.botflow.R

class AccountFragment(private val email: String) : Fragment() {
    private lateinit var viewModel: MainViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewModel = MainViewModel.getMainViewModel(email)
        return inflater.inflate(R.layout.account_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val emailTextView = view.findViewById<TextView>(R.id.email_textView)
        val planTextView = view.findViewById<TextView>(R.id.plan_textView)
        val planTextViewup = view.findViewById<TextView>(R.id.textView)
        val botsTextView = view.findViewById<TextView>(R.id.bots_textView)
        val progressBar = view.findViewById<ProgressBar>(R.id.account_progressBar)
        val accountLayout = view.findViewById<ConstraintLayout>(R.id.account_constraintLayout)
        planTextViewup.setTypeface(planTextViewup.getTypeface(), Typeface.BOLD)
        viewModel.account.observe(viewLifecycleOwner, Observer {
            emailTextView.text = it.email
            planTextView.text = it.plan
            botsTextView.text = it.bots.toString()
            progressBar.visibility = View.INVISIBLE
            accountLayout.visibility = View.VISIBLE
        })

    }
}