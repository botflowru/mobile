package com.example.botflow.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.botflow.models.Bot
import com.example.botflow.models.BotList
import com.example.botflow.repository.Repository

class MainViewModel(repository: Repository) : ViewModel(){
    val bots: LiveData<BotList> = repository.getBots()
    val bot = MutableLiveData<Bot>()

}