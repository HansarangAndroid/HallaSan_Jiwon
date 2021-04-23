package org.sopt.soptseminar.data.member

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface MemberDao {
    @Query("SELECT * FROM Member")
    fun getAll(): LiveData<List<Member>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(member: Member)

    @Delete
    fun delete(member: Member)
}