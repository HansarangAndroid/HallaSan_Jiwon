package org.sopt.soptseminar.login

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel(){
    val id = MutableLiveData<String>()
    val password = MutableLiveData<String>()

    fun checkInputText():Boolean{
        Log.d("LoginViewModelTest",id.value.toString()+"      "+password.value.toString())
        return id.value.isNullOrEmpty() || password.value.isNullOrEmpty()
    }

}