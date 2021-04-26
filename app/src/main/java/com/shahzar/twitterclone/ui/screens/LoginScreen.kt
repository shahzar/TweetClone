package com.shahzar.twitterclone.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigate
import com.shahzar.twitterclone.R
import com.shahzar.twitterclone.ui.main.MainViewModel

@Composable
fun LoginScreen(navController: NavHostController, viewModel: MainViewModel) {

    val email: String by viewModel.email.observeAsState("")
    val password: String by viewModel.password.observeAsState("")

    val paddingSmall = dimensionResource(id = R.dimen.padding_small)
    val paddingDef = dimensionResource(id = R.dimen.padding_default)


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(paddingDef),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Sign in to your Account")
        OutlinedTextField(
            value = email,
            onValueChange = { viewModel.onEmailChange(it) },
            label = {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_email_icon),
                        contentDescription = "Email icon"
                    )
                    Text(
                        text = "Email",
                        modifier = Modifier.padding(paddingSmall)
                    )
                }
            }
        )
        OutlinedTextField(
            value = password,
            onValueChange = { viewModel.onPasswordChange(it) },
            label = {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_password_lock),
                        contentDescription = "Password icon"
                    )
                    Text(
                        text = "Password",
                        modifier = Modifier.padding(paddingSmall)
                    )
                }
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { navController.navigate(Routes.HOMESCREEN) },
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(paddingSmall)

        ) {
            Text(text = "Login")
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}