package com.idnp.skinguardianapp.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.idnp.skinguardianapp.data.model.User

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey (autoGenerate = true) val uid: Int = 0,

    @ColumnInfo(name = "user") val userU: String,
    @ColumnInfo(name = "password") val passwordU: String,
)
fun User.toDataBase() = UserEntity( userU = user, passwordU = password)











