package com.idnp.skinguardianapp.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity

data class UserEntity(
    @PrimaryKey val uid: Int,
    @ColumnInfo(name = "name") val nameU: String?,
    @ColumnInfo(name = "last_name") val lastNameU: String?,
    @ColumnInfo(name = "user") val userU: String?,
    @ColumnInfo(name = "password") val passwordU: String?,
)
