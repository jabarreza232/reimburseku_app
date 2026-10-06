package id.co.evolution.reimbursekuapp.navigation

sealed class NavScreen(val route:String) {
    object Login: NavScreen("login_screen")
    object Onboarding: NavScreen("onboarding_screen")
    object Home: NavScreen("home_screen")


}