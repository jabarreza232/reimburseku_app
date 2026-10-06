package id.co.evolution.reimbursekuapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import id.co.evolution.reimbursekuapp.feature.auth.presentation.screen.LoginScreen
import id.co.evolution.reimbursekuapp.feature.home.presentation.screen.HomeScreen
import id.co.evolution.reimbursekuapp.feature.onboarding.presentation.screen.OnboardingScreen

@Composable
fun RootNavigationGraph(
    navController: NavHostController,
    isOnboardingCompleted: Boolean,
    onFinishOnboarding: () -> Unit
) {
    val startDestination = if (isOnboardingCompleted) NavScreen.Login.route else NavScreen.Onboarding.route

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(route = NavScreen.Onboarding.route) {
            OnboardingScreen(
                onFinishOnboarding = {
                    onFinishOnboarding()
                    navController.navigate(NavScreen.Login.route) {
                        popUpTo(NavScreen.Onboarding.route) { inclusive = true }
                    }
                }
            )
        }

        composable(route = NavScreen.Login.route) {
            LoginScreen(
                onNavigateHome = {
                    navController.navigate(NavScreen.Home.route) {
                        popUpTo(NavScreen.Login.route) { inclusive = true }
                    }
                }
            )
        }

        composable(route = NavScreen.Home.route) {
            HomeScreen()
        }
    }
}
