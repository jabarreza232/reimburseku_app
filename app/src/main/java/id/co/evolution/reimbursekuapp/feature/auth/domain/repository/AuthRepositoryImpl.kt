package id.co.evolution.reimbursekuapp.feature.auth.domain.repository

import id.co.evolution.reimbursekuapp.core.utils.Resource
import id.co.evolution.reimbursekuapp.feature.auth.data.remote.AuthApi
import id.co.evolution.reimbursekuapp.feature.auth.data.remote.dto.LoginRequestDto
import id.co.evolution.reimbursekuapp.feature.auth.domain.model.AuthUser
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val api: AuthApi
): AuthRepository{
    override suspend fun login(email: String, password: String): Flow<Resource<AuthUser>> = flow {
            emit(Resource.Loading())
        try{
            val response = api.login(LoginRequestDto(email,password))
            val authUser = AuthUser(
                id = response.user.idEmployee,
                name = response.user.nameEmployee,
                email = response.user.emailEmployee,
                roleName = response.role.roleName,
                token = response.token
            )
            emit(Resource.Success(authUser))
        }catch (e: HttpException){
            emit(Resource.Error("Login Gagal: {${e.message}}"))
        }catch (e: IOException){
            emit(Resource.Error("Tidak ada koneksi internet: {${e.message}}"))

        }
    }
}