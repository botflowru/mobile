package com.example.botflow.di

import com.example.botflow.remote_data.WebService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class RepositoryModule {
    @Provides
    fun webService(retrofit: Retrofit) : WebService{
        return retrofit.create(WebService::class.java)
    }

    @Provides
    fun retrofit(gsonConverterFactory: GsonConverterFactory): Retrofit{
        return Retrofit.Builder()
            .baseUrl("http://89.108.103.185:8080")
            .addConverterFactory(gsonConverterFactory)
            .build()
    }

    @Provides
    fun gsonConverterFactory(gson: Gson): GsonConverterFactory{
        return GsonConverterFactory
            .create(gson)
    }

    @Provides
    fun gson(): Gson{
        return GsonBuilder()
            .create()
    }
}