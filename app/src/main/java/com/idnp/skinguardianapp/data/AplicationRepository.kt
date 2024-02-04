package com.idnp.skinguardianapp.data

import com.idnp.skinguardianapp.data.database.dao.UserDao
import com.idnp.skinguardianapp.data.database.entities.toDataBase
import com.idnp.skinguardianapp.data.model.User
import com.idnp.skinguardianapp.data.model.toDomain
import javax.inject.Inject

class ApplicationRepository @Inject constructor(
    private val userDao: UserDao
) {
    fun getAllUsers(): List<User> {
        return userDao.getAllUsers().map { it.toDomain()}

    }
    fun insertUser(user: User) {
        userDao.insertUser(user.toDataBase())
    }

    fun getUser(id:Int):User{
        return userDao.getUser(id).toDomain()
    }
}