package com.idnp.skinguardianapp.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.idnp.skinguardianapp.data.database.entities.UserEntity

@Dao
interface UserDao {
    @Query("SELECT * FROM UserEntity")
    fun getAll(): List<UserEntity>

    @Query("SELECT * FROM UserEntity WHERE uid IN (:uids)")
    fun loadAllByIds(uids: IntArray): List<UserEntity>

    @Insert
    fun insertAll(vararg users: UserEntity )

    @Delete
    fun delete(user: UserEntity)
}