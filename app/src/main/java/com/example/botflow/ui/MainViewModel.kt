package com.example.botflow.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.botflow.di.DaggerRepositoryComponent
import com.example.botflow.models.Account
import com.example.botflow.models.Bot
import com.example.botflow.models.BotList
import com.example.botflow.repository.Repository

class MainViewModel(private val repository: Repository, val email: String) : ViewModel(){
    val account: LiveData<Account> = repository.getAccount(email)
    val bot: MutableLiveData<Bot> = MutableLiveData()

    fun saveBot(name: String){
        Thread {
            repository.saveBot(name, email)
        }.start()
    }

    fun updateBot(id: Int, name: String){
        Thread {
            repository.updateBot(id, name)
        }.start()
    }
    fun updateList() : LiveData<BotList> {
        return repository.getBots(email)
    }
    companion object {
        fun getMainViewModel(email: String) : MainViewModel {
            val repositoryComponent = DaggerRepositoryComponent.create()
            val repository = repositoryComponent.getRepository()
            return MainViewModel(repository, email)
        }
    }
}