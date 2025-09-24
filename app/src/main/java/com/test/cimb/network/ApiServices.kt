package com.test.cimb.network

import com.test.cimb.model.User
import retrofit2.http.GET

interface ApiServices {

    @GET("users")
    suspend fun getUsers(): List<User>

}