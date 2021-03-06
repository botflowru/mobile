package com.example.botflow.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.botflow.R
import com.example.botflow.activities.BotSettingsActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton


class BotListFragment(private val email: String) : Fragment(), ViewTreeObserver.OnWindowFocusChangeListener {
    private lateinit var viewModel: MainViewModel
    private lateinit var botListRecyclerView: RecyclerView
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewModel = MainViewModel.getMainViewModel(email)
        return inflater.inflate(R.layout.bot_list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val addBotButton = view.findViewById<FloatingActionButton>(R.id.addBotButton)
        botListRecyclerView = view.findViewById(R.id.bot_list_recyclerView)
        val progressBar = view.findViewById<ProgressBar>(R.id.bot_list_progressBar)
        botListRecyclerView.layoutManager = LinearLayoutManager(context)
        addBotButton.setOnClickListener {
            val dialog = SaveBotDialogFragment(viewModel)
            dialog.show(activity!!.supportFragmentManager, null)
        }
        viewModel.updateList().observe(viewLifecycleOwner, Observer {
            if(it == null) {
                val errorDialog = ErrorDialog()
                errorDialog.show(activity!!.supportFragmentManager, null)
            }
            else {
                botListRecyclerView.adapter = BotListRecyclerViewAdapter(it, viewModel)
                botListRecyclerView.visibility = View.VISIBLE
                addBotButton.visibility = View.VISIBLE
                progressBar.visibility = View.INVISIBLE
            }

        })
        viewModel.bot.observe(viewLifecycleOwner, Observer {
            val intent = Intent(activity, BotSettingsActivity::class.java)
            intent.putExtra("bot", it)
            startActivity(intent)
        })
        view.viewTreeObserver.addOnWindowFocusChangeListener(this)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        view!!.viewTreeObserver.removeOnWindowFocusChangeListener(this)
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        if (hasFocus){
            viewModel.updateList().observe(viewLifecycleOwner, Observer {
                if(it == null) {
                    val errorDialog = ErrorDialog()
                    errorDialog.show(activity!!.supportFragmentManager, null)
                }
                else {
                    botListRecyclerView.adapter = BotListRecyclerViewAdapter(it, viewModel)
                }
            })
        }
    }
}
