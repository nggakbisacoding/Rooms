package com.trpl.rooms

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface DAO {
    @Insert
    suspend fun insertTask(user: UserModel)

    @Update
    suspend fun updateTask(user: UserModel)

    @Delete
    suspend fun deleteTask(user: UserModel)

    @Query("Delete from user")
    suspend fun deleteAll()

    @Query("Select * from user")
    suspend fun getTasks():List<UserModel>
}