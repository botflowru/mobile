package com.example.botflow.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.botflow.R
import com.example.botflow.models.Bot
import com.example.botflow.models.BotList

class BotListRecyclerViewAdapter(private val botList: BotList, private val viewModel: MainViewModel) : RecyclerView.Adapter<BotListRecyclerViewAdapter.BotListViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BotListViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return BotListViewHolder(layoutInflater, parent)
    }

    override fun getItemCount(): Int {
        return botList.bots.size
    }

    override fun onBindViewHolder(holder: BotListViewHolder, position: Int) {
        holder.bind(botList.bots[position])
    }

    inner class BotListViewHolder(inflater: LayoutInflater, viewGroup: ViewGroup)
        : RecyclerView.ViewHolder(inflater.inflate(R.layout.bot_list_item, viewGroup, false)) {

        fun bind(bot: Bot) {
            val botNameTextView = itemView.findViewById<TextView>(R.id.bot_name_textView)
            botNameTextView.text = bot.name
            itemView.setOnClickListener {
                viewModel.bot.value = bot
            }
        }
    }
}