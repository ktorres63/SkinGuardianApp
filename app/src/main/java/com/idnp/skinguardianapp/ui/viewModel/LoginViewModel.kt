package com.idnp.skinguardianapp.ui.viewModel

import androidx.lifecycle.ViewModel
import com.idnp.skinguardianapp.data.ApplicationRepository
import com.idnp.skinguardianapp.data.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor (
    private val repository: ApplicationRepository,private val miString: String
) : ViewModel() {
    private val users = repository.getAllUsers()
    private var index: Int = 0
    fun getSuccessfulLogin(user: User): Boolean {

        if (users.isNotEmpty()) {
            if (users.contains(user)){
                index = users.indexOf(user)
            }
            return true
        }
        return false
    }
    fun getUser():User{
        return users[index]
    }
}