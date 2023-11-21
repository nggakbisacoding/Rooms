package com.trpl.rooms

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [UserModel::class],version=1, exportSchema = false)
abstract class UserDatabase : RoomDatabase() {
    abstract fun dao():DAO
}