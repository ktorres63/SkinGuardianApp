package com.idnp.skinguardianapp.data.database.entities

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "users")
@Parcelize
data class UserEntity(
    @PrimaryKey (autoGenerate = true) val uid: Int,
    @ColumnInfo(name = "name") val nameU: String?,
    @ColumnInfo(name = "last_name") val lastNameU: String?,
    @ColumnInfo(name = "user") val userU: String?,
    @ColumnInfo(name = "password") val passwordU: String?,
): Parcelable










