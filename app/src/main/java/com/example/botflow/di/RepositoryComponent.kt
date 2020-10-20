package com.example.botflow.di

import com.example.botflow.repository.Repository
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [RepositoryModule::class])
interface RepositoryComponent {
    fun getRepository(): Repository
}