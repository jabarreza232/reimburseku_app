package id.co.evolution.reimbursekuapp.feature.home.data.remote.dto

import com.google.gson.annotations.SerializedName

data class LoginResponseDto (
    @SerializedName("message") val message:String,
    @SerializedName("user") val user: UserDto,
    @SerializedName("role") val role: RoleDto,
    @SerializedName("token") val token:String,

    )

data class UserDto(
    @SerializedName("id_employee") val idEmployee:Int,
    @SerializedName("name") val nameEmployee:String,
    @SerializedName("email") val emailEmployee:String,
    @SerializedName("phone") val phoneNumber: String,
    @SerializedName("address") val address: String,
    @SerializedName("position") val position:String,
    @SerializedName("gender") val gender: String,

    )
data class RoleDto(
    @SerializedName("role_name") val roleName: String,
    @SerializedName("slug") val slug: String,
    )