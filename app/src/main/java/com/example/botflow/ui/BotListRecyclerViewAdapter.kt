package com.example.botflow.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.botflow.R
import com.example.botflow.models.Bot

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

        fun bind(bot: Bot) {

        }
    }
}