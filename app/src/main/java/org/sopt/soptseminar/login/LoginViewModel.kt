package org.sopt.soptseminar.login

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import org.sopt.soptseminar.login.loginApi.SignupClient
import org.sopt.soptseminar.login.loginApi.RequestLoginData
import org.sopt.soptseminar.login.loginApi.ResponseLoginData
import org.sopt.soptseminar.data.member.MemberRepository
import org.sopt.soptseminar.login.loginApi.LoginClient
import org.sopt.soptseminar.util.MyApplication
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel(application: Application) : AndroidViewModel(application){
    private val repository = MemberRepository(MyApplication.ApplicationContext() as Application)

    val id = MutableLiveData<String>()
    val password = MutableLiveData<String>()

    private val _loginSuccess = MutableLiveData<Boolean>()
    val loginSuccess: LiveData<Boolean>
        get() = _loginSuccess

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

    fun doLogin(){

        val requestLoginData = RequestLoginData(
            email = id.value.toString(),
            password = password.value.toString()
        )
        Log.d("TEST_LOGINVIEWMODEL",requestLoginData.email+" "+requestLoginData.password)
        val call: Call<ResponseLoginData> = LoginClient.loginService
            .postLogin(requestLoginData)

        call.enqueue(object : Callback<ResponseLoginData> {
            override fun onResponse(
                call: Call<ResponseLoginData>,
                response: Response<ResponseLoginData>
            ){
                Log.d("TEST_LOGINVIEWMODEL",response.isSuccessful.toString())
                _loginSuccess.value = response.isSuccessful
            }

            override fun onFailure(call: Call<ResponseLoginData>, t: Throwable) {
                Log.d("NetworkTest","error:$t")
            }
        })
    }

    fun setSuccessFalse(){
        _loginSuccess.value = false
    }
}