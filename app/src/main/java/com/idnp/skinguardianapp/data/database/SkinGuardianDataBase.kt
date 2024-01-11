package com.idnp.skinguardianapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.idnp.skinguardianapp.data.database.dao.UserDao
import com.idnp.skinguardianapp.data.database.entities.UserEntity

@Database(entities = [ UserEntity::class],version = 1, exportSchema = false)
abstract class SkinGuardianDataBase: RoomDatabase()  {
    abstract fun getUsereDao(): UserDao

}