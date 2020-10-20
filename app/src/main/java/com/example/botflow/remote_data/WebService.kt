package com.example.botflow.remote_data

import com.example.botflow.models.Account
import com.example.botflow.models.BotList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WebService {
    @GET("/bots")
    fun getBots(@Query("email") email: String) : Call<BotList>
    @GET("/account")
    fun getAccount(@Query("email") email: String) : Call<Account>
}