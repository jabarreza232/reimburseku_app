package id.co.evolution.reimbursekuapp.feature.auth.presentation.state

import id.co.evolution.reimbursekuapp.feature.auth.domain.model.AuthUser

data class LoginState(
    val isLoading: Boolean = false,
    val user: AuthUser? = null,
    val error: String = ""
)