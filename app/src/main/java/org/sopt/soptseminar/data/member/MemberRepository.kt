package org.sopt.soptseminar.data.member

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import java.util.*

class MemberRepository(application:Application) {

    private val memberDatabase = MemberDatabase.getInstance(application)!!
    private val memberDao : MemberDao = memberDatabase.memberDao()
    private val members : LiveData<List<Member>> = memberDao.getAll()

    fun getAll(): LiveData<List<Member>>{
        return members
    }

    fun insert(member : Member){
        try{
            val thread = Thread(Runnable{
                memberDao.insert(member)
            })
            thread.start()
        }catch(e:Exception){
            Log.d("CONTACT_REPOSITORY","insert error")
        }
    }

    fun delete(member: Member){
        try{
            val thread = Thread(Runnable{
                memberDao.delete(member)
            })
            thread.start()
        }catch(e:Exception){
            Log.d("CONTACT_REPOSITORY","delete error")
        }
    }

    fun findById(userId : String) : Member? { //userId로 검사해서 member리턴
        var member: Member? = null
        try{
            val thread = Thread {
                System.out.println("들ㅇ왓???")
                member = memberDao.findById(userId)
                System.out.println("aaaaㅙㅙ"+member)
            }
            thread.start()
        }catch(e:Exception){
            Log.d("CONTACT_REPOSITORY","findById error")
        }
        System.out.println("aaaa"+member)
        return member
    }
}