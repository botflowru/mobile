package com.example.botflow.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.botflow.R
import com.example.botflow.models.Bot

class BotListFragment(private val botList: List<Bot>) : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.bot_list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val botListRecyclerView = view.findViewById<RecyclerView>(R.id.bot_list_recyclerView)
        botListRecyclerView.adapter = BotListRecyclerViewAdapter(botList)
    }
}
class BotListRecyclerViewAdapter(private val botList: List<Bot>) : RecyclerView.Adapter<BotListRecyclerViewAdapter.BotListViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BotListViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return BotListViewHolder(layoutInflater, parent)
    }

    override fun getItemCount(): Int {
        return botList.size
    }

    override fun onBindViewHolder(holder: BotListViewHolder, position: Int) {
        holder.bind(botList[position])
    }

    inner class BotListViewHolder(inflater: LayoutInflater, viewGroup: ViewGroup)
        : RecyclerView.ViewHolder(inflater.inflate(R.layout.bot_list_item, viewGroup, false)) {

        fun bind(bot: Bot){

        }

    }
}