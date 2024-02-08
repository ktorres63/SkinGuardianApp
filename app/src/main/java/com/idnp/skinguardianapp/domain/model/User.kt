package com.idnp.skinguardianapp.domain.model

import com.idnp.skinguardianapp.data.database.entities.UserEntity

data class User(val uid: Int, val user: String, val password: String){
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as User
        if (user != other.user) return false
        return password == other.password
    }
}

fun UserEntity.toDomain() = User(uid,userU,passwordU)
