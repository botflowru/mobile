package com.example.botflow.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.botflow.di.DaggerRepositoryComponent
import com.example.botflow.models.Account
import com.example.botflow.models.Bot
import com.example.botflow.models.BotList
import com.example.botflow.repository.Repository
import java.io.Serializable

class MainViewModel(private val repository: Repository, val email: String) : ViewModel(), Serializable{
    val account: LiveData<Account> = repository.getAccount(email)
    val bots: LiveData<BotList> = repository.getBots(email)
    val bot: MutableLiveData<Bot> = MutableLiveData()

    fun saveBot(bot: Bot){
        Thread {
            repository.saveBot(bot)
        }.start()
    }

    fun updateBot(bot: Bot){
        Thread {
            repository.updateBot(bot)
        }.start()
    }
    companion object {
        fun getMainViewModel(email: String) : MainViewModel {
            val repositoryComponent = DaggerRepositoryComponent.create()
            val repository = repositoryComponent.getRepository()
            return MainViewModel(repository, email)
        }
    }
}