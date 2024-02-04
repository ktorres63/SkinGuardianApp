package com.idnp.skinguardianapp.domain

import com.idnp.skinguardianapp.data.ApplicationRepository
import com.idnp.skinguardianapp.data.model.User
import javax.inject.Inject

class InsertUserUseCase @Inject constructor (
    private val repository: ApplicationRepository
) {
    operator fun invoke(user: User) {
        repository.insertUser(user)
    }
}


















