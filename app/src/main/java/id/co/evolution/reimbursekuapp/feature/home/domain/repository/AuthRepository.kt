package id.co.evolution.reimbursekuapp.feature.home.domain.repository

import id.co.evolution.reimbursekuapp.core.utils.Resource
import id.co.evolution.reimbursekuapp.feature.auth.domain.model.AuthUser
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun login(email: String, password: String): Flow<Resource<AuthUser>>

}