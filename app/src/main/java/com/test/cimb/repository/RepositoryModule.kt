package com.test.cimb.repository

import com.test.cimb.network.ApiServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Provides
    fun providesUserRepository(apiServices: ApiServices): UserRepository =
        UserRepositoryImpl(apiServices)
}