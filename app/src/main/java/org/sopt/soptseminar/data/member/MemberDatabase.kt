package org.sopt.soptseminar.data.member

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Member::class], version=2,exportSchema = false)
abstract class MemberDatabase :RoomDatabase() {
    abstract fun memberDao(): MemberDao

    //instance SingleTone생성
    companion object{
        private var INSTANCE: MemberDatabase? = null

        fun getInstance(context : Context): MemberDatabase?{
            if(INSTANCE == null){
                synchronized(MemberDatabase::class){ //스레드 충돌 방지
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        MemberDatabase::class.java,"member")
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return INSTANCE
        }
    }
}