package com.example.botflow.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.botflow.models.BotList
import com.example.botflow.remote_data.WebService

class Repository(private val webService: WebService) {
    fun getBots() : LiveData<BotList>{
        val data = MutableLiveData<BotList>()
        data.value = webService.getBots()
        return data
    }
}