package com.idnp.skinguardianapp.ui.viewModel

import androidx.lifecycle.ViewModel
import com.idnp.skinguardianapp.data.ApplicationRepository
import com.idnp.skinguardianapp.data.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
     private val repository: ApplicationRepository
) : ViewModel() {
    fun insertUserInDatabase(user: User) {
        repository.insertUser(user)
    }
    //TODO: crear un ViewMOdel para PRofile que sea GetUserFromDataBase(int)
}