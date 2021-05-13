package org.sopt.soptseminar.login.loginApi

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object SignupClient {
    private const val BASE_URL = "http://cherishserver.com"

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val signupService : SignupService = retrofit.create(SignupService::class.java)
}