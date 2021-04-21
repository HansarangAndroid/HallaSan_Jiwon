package org.sopt.soptseminar.signup

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SignUpViewModel : ViewModel(){
    val name = MutableLiveData<String>()
    val id = MutableLiveData<String>()
    val password = MutableLiveData<String>()

    fun checkInputText():Boolean{
        Log.d("SignupViewModelTest",name.value.toString()+"      "+id.value.toString()+"      "+password.value.toString())
        return name.value.isNullOrEmpty() || id.value.isNullOrEmpty() || password.value.isNullOrEmpty()
    }

}