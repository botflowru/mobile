package com.example.botflow.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.botflow.models.Account
import com.example.botflow.models.BotList
import com.example.botflow.repository.Repository

class MainViewModel(repository: Repository, email: String) : ViewModel(){
    val account: LiveData<Account> = repository.getAccount(email)
    val bots: LiveData<BotList> = repository.getBots(email)
}