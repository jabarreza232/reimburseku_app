package id.co.evolution.reimbursekuapp.feature.auth.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import id.co.evolution.reimbursekuapp.feature.auth.presentation.state.LoginState
import id.co.evolution.reimbursekuapp.feature.auth.presentation.viewmodel.LoginViewModel
import id.co.evolution.reimbursekuapp.ui.theme.ReimbursekuAppTheme

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel(),
    onNavigateHome: () -> Unit
) {
    val state by viewModel.state.collectAsState()
    LoginContent(
        state = state,
        onLogin = { email, password ->
            viewModel.login(email, password)
        },
        onNavigateHome = onNavigateHome
    )

}

@Composable
fun LoginContent(
    state: LoginState,
    onLogin: (String, String) -> Unit,
    onNavigateHome: () -> Unit
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    LaunchedEffect(key1 = state.user) {
        if (state.user != null) {
            onNavigateHome()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TextField(value = email,
            onValueChange = { email = it },
            label = { Text("email") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = password,
            onValueChange = {password = it},
            label= {Text("password")},
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation()
        )
        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = { onLogin(email,password) },
            modifier = Modifier.fillMaxWidth(),
            enabled = !state.isLoading

        ) {
            if(state.isLoading){
                CircularProgressIndicator(color = Color.White, modifier = Modifier.size(24.dp))
            }else{
                Text("Login")
            }

        }

        if(state.error.isNotBlank()){
            Text(text = state.error, color = Color.Red, modifier = Modifier.padding(top = 16.dp))
        }

    }
}


@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    ReimbursekuAppTheme {
        LoginContent(state = LoginState(), onLogin = { _, _ -> }, onNavigateHome = {})
    }
}