package com.example.taxbandits.di

import com.example.taxbandits.data.repository.ChatApiImplementation
import com.example.taxbandits.domain.repository.ApiServicesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract  class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindProductRepository(impl : ChatApiImplementation) : ApiServicesRepository
}