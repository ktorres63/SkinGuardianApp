package com.idnp.skinguardianapp.ui.viewModel

import androidx.lifecycle.ViewModel
import com.idnp.skinguardianapp.data.model.User
import com.idnp.skinguardianapp.domain.InsertUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val insertUserUseCase: InsertUserUseCase
) : ViewModel() {
    fun insertUserInDatabase(user: User) {
        insertUserUseCase.invoke(user)
    }
}