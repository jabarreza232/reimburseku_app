package id.co.evolution.reimbursekuapp.feature.auth.domain.model

data class AuthUser (
    val id:Int,
    val name:String,
    val email:String,
    val roleName:String,
    val token:String
)