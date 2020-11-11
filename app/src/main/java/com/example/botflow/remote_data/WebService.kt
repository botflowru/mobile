package com.example.botflow.remote_data

import com.example.botflow.models.Account
import com.example.botflow.models.BotList
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WebService {
    @GET("/bots")
    fun getBots(@Query("email") email: String) : Call<BotList>

    @GET("/user")
    fun getAccount(@Query("email") email: String) : Call<Account>

    @GET("/save_bot")
    fun saveBot(@Query("name") name: String, @Query("email") email: String) : Call<ResponseBody>
}