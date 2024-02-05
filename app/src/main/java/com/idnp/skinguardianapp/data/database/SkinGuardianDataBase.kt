package com.idnp.skinguardianapp.data.database

import android.app.Application
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.idnp.skinguardianapp.data.database.dao.UserDao
import com.idnp.skinguardianapp.data.database.entities.UserEntity
import dagger.hilt.android.HiltAndroidApp

@Database(entities = [ UserEntity::class],version = 1, exportSchema = false)
abstract class SkinGuardianDataBase: RoomDatabase()  {
    abstract fun getUserDao(): UserDao
}

@HiltAndroidApp
class SkinGuardian: Application() {
    var userId: Int = 1
}

















