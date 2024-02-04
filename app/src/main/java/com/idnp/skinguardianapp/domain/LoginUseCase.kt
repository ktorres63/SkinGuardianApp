package com.idnp.skinguardianapp.domain

import com.idnp.skinguardianapp.data.ApplicationRepository
import com.idnp.skinguardianapp.data.model.User
import javax.inject.Inject

class LoginUseCase @Inject constructor (
    private val repository: ApplicationRepository
) {
    operator fun invoke(user: User): Boolean {
        val users = repository.getAllUsers()
        return if (users.isNotEmpty()) {
            users.contains(user)
        } else {
            false
        }
    }
}