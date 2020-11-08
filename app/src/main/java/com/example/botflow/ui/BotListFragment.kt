package com.example.botflow.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.example.botflow.R
import com.example.botflow.activities.AddBotActivity


class BotListFragment(private val email: String) : Fragment() {
    private lateinit var viewModel: MainViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewModel = MainViewModel.getMainViewModel(email)
        return inflater.inflate(R.layout.bot_list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val addBotButton = view.findViewById<Button>(R.id.addBotButton)
        val botListRecyclerView = view.findViewById<RecyclerView>(R.id.bot_list_recyclerView)
        addBotButton.setOnClickListener {
            val intent = Intent(activity, AddBotActivity::class.java)
            intent.putExtra("email", email)
            startActivity(intent)
        }
        viewModel.bots.observe(viewLifecycleOwner, Observer {
            botListRecyclerView.adapter = BotListRecyclerViewAdapter(it)
        })
    }
}
