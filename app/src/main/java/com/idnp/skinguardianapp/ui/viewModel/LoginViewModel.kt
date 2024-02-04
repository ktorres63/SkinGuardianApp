package com.idnp.skinguardianapp.ui.viewModel

import androidx.lifecycle.ViewModel
import com.idnp.skinguardianapp.data.model.User
import com.idnp.skinguardianapp.domain.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor (
    private val loginUseCase: LoginUseCase
) : ViewModel() {
    fun getSuccessfulLogin(user: User): Boolean {
        return loginUseCase.invoke(user)
    }
}