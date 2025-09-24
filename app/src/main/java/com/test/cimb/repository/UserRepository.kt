package com.test.cimb.repository

import com.test.cimb.model.User

interface UserRepository {

    suspend fun getUsers(): List<User>

}