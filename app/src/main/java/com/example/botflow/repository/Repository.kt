package com.example.botflow.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.botflow.models.Account
import com.example.botflow.models.BotList
import com.example.botflow.remote_data.WebService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor(private val webService: WebService) {
    fun getBots(email: String) : LiveData<BotList> {
        val data = MutableLiveData<BotList>()
        webService.getBots(email).enqueue(object : Callback<BotList> {
            override fun onResponse(call: Call<BotList>, response: Response<BotList>) {
                data.value = response.body()

            }

            override fun onFailure(call: Call<BotList>, t: Throwable) {

            }
        })
        return data
    }

    fun getAccount(email: String) : LiveData<Account> {
        val data = MutableLiveData<Account>()
        webService.getAccount(email).enqueue(object : Callback<Account> {
            override fun onResponse(call: Call<Account>, response: Response<Account>) {
                data.value = response.body()
            }

            override fun onFailure(call: Call<Account>, t: Throwable) {

            }
        })
        return data
    }

    fun saveBot(name: String, email: String) {
        webService.saveBot(name, email).execute()
    }
}