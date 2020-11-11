package com.example.botflow.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.botflow.R
import com.example.botflow.activities.BotSettingsActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton


class BotListFragment(private val email: String) : Fragment() {
    private lateinit var viewModel: MainViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewModel = MainViewModel.getMainViewModel(email)
        return inflater.inflate(R.layout.bot_list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val addBotButton = view.findViewById<FloatingActionButton>(R.id.addBotButton)
        val botListRecyclerView = view.findViewById<RecyclerView>(R.id.bot_list_recyclerView)
        val progressBar = view.findViewById<ProgressBar>(R.id.bot_list_progressBar)
        botListRecyclerView.layoutManager = LinearLayoutManager(context)
        addBotButton.setOnClickListener {
            val dialog = SaveBotDialogFragment(viewModel)
            dialog.show(activity!!.supportFragmentManager, null)
        }
        viewModel.bots.observe(viewLifecycleOwner, Observer {
            botListRecyclerView.adapter = BotListRecyclerViewAdapter(it, viewModel)
            botListRecyclerView.visibility = View.VISIBLE
            addBotButton.visibility = View.VISIBLE
            progressBar.visibility = View.INVISIBLE

        })
        viewModel.bot.observe(viewLifecycleOwner, Observer {
            val intent = Intent(activity, BotSettingsActivity::class.java)
            intent.putExtra("bot", it)
            startActivity(intent)
        })
    }
}
