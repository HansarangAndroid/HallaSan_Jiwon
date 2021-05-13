package org.sopt.soptseminar.login.loginApi

import com.google.gson.annotations.SerializedName

data class RequestLoginData(
    @SerializedName("email") //api와 동일한 이름 연결해줌
    val email: String,
    val password: String
)
