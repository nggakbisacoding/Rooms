package com.trpl.rooms

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class UserModel(
    @PrimaryKey(autoGenerate = true)
    var id:Int,
    var name:String,
    var phone:String,
    var email:String,
    var pass:String
)