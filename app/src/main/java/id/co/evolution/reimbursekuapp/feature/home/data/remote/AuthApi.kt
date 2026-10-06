package id.co.evolution.reimbursekuapp.feature.home.data.remote

import id.co.evolution.reimbursekuapp.feature.auth.data.remote.dto.LoginRequestDto
import id.co.evolution.reimbursekuapp.feature.auth.data.remote.dto.LoginResponseDto
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("login")
    suspend fun login(@Body request: LoginRequestDto): LoginResponseDto

}