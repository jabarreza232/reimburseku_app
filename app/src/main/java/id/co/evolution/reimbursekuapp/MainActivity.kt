package id.co.evolution.reimbursekuapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import id.co.evolution.reimbursekuapp.core.presentation.MainViewModel
import id.co.evolution.reimbursekuapp.navigation.RootNavigationGraph
import id.co.evolution.reimbursekuapp.ui.theme.ReimbursekuAppTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ReimbursekuAppTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    val navController = rememberNavController()
                    val isOnboardingCompleted by mainViewModel.isOnboardingCompleted.collectAsStateWithLifecycle()

                    RootNavigationGraph(
                        navController = navController,
                        isOnboardingCompleted = isOnboardingCompleted,
                        onFinishOnboarding = { mainViewModel.finishOnboarding() }
                    )
                }
            }
        }
    }
}
