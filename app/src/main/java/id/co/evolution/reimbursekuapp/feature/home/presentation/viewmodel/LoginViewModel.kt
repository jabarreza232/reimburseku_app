package id.co.evolution.reimbursekuapp.feature.home.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import id.co.evolution.reimbursekuapp.core.utils.Resource
import id.co.evolution.reimbursekuapp.feature.auth.domain.model.AuthUser
import id.co.evolution.reimbursekuapp.feature.auth.domain.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class LoginState(
    val isLoading: Boolean = false,
    val user: AuthUser? = null,
    val error: String = ""
)
@HiltViewModel
class LoginViewModel @Inject constructor(private val repository: AuthRepository) : ViewModel() {
        private val _state = MutableStateFlow(LoginState())
        val state: StateFlow<LoginState> = _state.asStateFlow()

    fun login(email:String, password:String){
        viewModelScope.launch {
            repository.login(email, password).collect {
                result->
                when(result){
                    is Resource.Success -> _state.value = LoginState(user = result.data)
                    is Resource.Error -> _state.value = LoginState(error = result.message ?: "Error")
                    is Resource.Loading -> _state.value = LoginState(isLoading = true)
                }
            }
        }
    }
}