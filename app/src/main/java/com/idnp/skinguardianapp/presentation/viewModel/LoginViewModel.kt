package com.idnp.skinguardianapp.presentation.viewModel

import androidx.lifecycle.ViewModel
import com.idnp.skinguardianapp.data.ApplicationRepository
import com.idnp.skinguardianapp.domain.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor (private val repository: ApplicationRepository) : ViewModel() {
    private val users = repository.getAllUsers()
    private var index = 1

    fun getSuccessfulLogin(user: User): Boolean {

        if (users.isNotEmpty() && users.contains(user)) {
                index = users.indexOf(user)
                return true
        }
        return false

    }
    fun getIndexUser():Int{
        return index+1
    }

}