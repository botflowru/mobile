package com.example.botflow.remote_data

import com.example.botflow.models.BotList

interface WebService {
    fun getBots() : BotList
}