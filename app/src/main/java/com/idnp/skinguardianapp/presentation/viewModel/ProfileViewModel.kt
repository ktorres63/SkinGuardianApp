package com.idnp.skinguardianapp.presentation.viewModel


import androidx.lifecycle.ViewModel
import com.idnp.skinguardianapp.data.ApplicationRepository
import com.idnp.skinguardianapp.domain.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val repository: ApplicationRepository
) : ViewModel() {

    fun getUser(id: Int): User {
        return repository.getUser(id)
    }


}