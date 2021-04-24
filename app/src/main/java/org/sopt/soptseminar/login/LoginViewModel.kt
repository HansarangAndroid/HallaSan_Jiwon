package org.sopt.soptseminar.login

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.sopt.soptseminar.data.member.MemberRepository
import org.sopt.soptseminar.util.MyApplication

class LoginViewModel(application: Application) : AndroidViewModel(application){
    private val repository = MemberRepository(MyApplication.ApplicationContext() as Application)

    val id = MutableLiveData<String>()
    val password = MutableLiveData<String>()

    fun checkInputText():Boolean{
        Log.d("LoginViewModelTest",id.value.toString()+"      "+password.value.toString())
        return id.value.isNullOrEmpty() || password.value.isNullOrEmpty()
    }

    fun isLogin(id:String,pw:String): Int {
        //SignUpViewModel에서 사용한 repository와 다른 repository.
        //application주입 때문인가 싶어서 MyApplication 클래스 만들어서 주입했지만 여전히 다름 뭐지?!
        val member = repository.findById(id)
        Log.d("TESTLOGIN",member.toString())
        if (member != null) {
            if(member.password != pw)
                return 1//wrond PW
        }
        return 2 //No ID
    }
}