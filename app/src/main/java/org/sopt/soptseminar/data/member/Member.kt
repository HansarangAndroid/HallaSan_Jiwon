package org.sopt.soptseminar.data.member

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Member")
data class Member (
        @PrimaryKey(autoGenerate = true)
        var id:Long?,
        @ColumnInfo(name = "name")
        var name: String?,
        @ColumnInfo(name = "userId")
        var userId:String?,
        @ColumnInfo(name = "password")
        var password:String?
){
    constructor() : this(null,"","","")
}