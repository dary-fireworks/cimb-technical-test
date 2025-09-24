package com.test.cimb.repository

import com.test.cimb.model.User
import com.test.cimb.network.ApiServices
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val apiServices: ApiServices
): UserRepository {

    override suspend fun getUsers(): List<User> {
        return apiServices.getUsers()
    }
}