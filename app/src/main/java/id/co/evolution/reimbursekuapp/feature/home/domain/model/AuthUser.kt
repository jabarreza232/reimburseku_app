package id.co.evolution.reimbursekuapp.feature.home.domain.model

data class AuthUser (
    val id:Int,
    val name:String,
    val email:String,
    val roleName:String,
    val token:String
)