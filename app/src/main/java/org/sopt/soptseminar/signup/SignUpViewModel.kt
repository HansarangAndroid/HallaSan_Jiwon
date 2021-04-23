package org.sopt.soptseminar.signup

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import org.sopt.soptseminar.data.member.Member
import org.sopt.soptseminar.data.member.MemberRepository
import org.sopt.soptseminar.util.MyApplication

class SignUpViewModel(application: Application) : AndroidViewModel(application){
    private val repository = MemberRepository(MyApplication.ApplicationContext() as Application) //activity의 생명주기가 아닌 application의 생명주기를 따르도록 함
    private val members = repository.getAll()

    val name = MutableLiveData<String>()
    val id = MutableLiveData<String>()
    val password = MutableLiveData<String>()

    fun checkInputText():Boolean{
        Log.d("SignupViewModelTest",name.value.toString()+"      "+id.value.toString()+"      "+password.value.toString())
        return name.value.isNullOrEmpty() || id.value.isNullOrEmpty() || password.value.isNullOrEmpty()
    }

    //회원 정보 확인 위한 test function
    fun getAll(): LiveData<List<Member>>{
        return this.members
    }

    fun insert(member: Member){
        repository.insert(member)
    }

    fun delete(member: Member){
        repository.delete(member)
    }

    fun isDuplicate(userId:String) :Boolean{
        Log.d("TESTDUP",repository.findById(userId).toString())
        return repository.findById(userId) != null //duplicate -> true
    }
}