package com.shahzar.twitterclone.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeOptions
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigate
import com.google.firebase.auth.FirebaseUser
import com.shahzar.twitterclone.R
import com.shahzar.twitterclone.ui.main.MainViewModel
import com.shahzar.twitterclone.util.Resource

@Composable
fun LoginScreen(navController: NavHostController, viewModel: MainViewModel) {

    val email: String by viewModel.email.observeAsState("")
    val password: String by viewModel.password.observeAsState("")
    val loginState: Resource<FirebaseUser> by viewModel.loginState.observeAsState(Resource.Loading())

    val paddingSmall = dimensionResource(id = R.dimen.padding_small)
    val paddingDef = dimensionResource(id = R.dimen.padding_default)


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(paddingDef),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        val imageModifier = Modifier

        val image = painterResource(id = R.drawable.ic_teamio_logo)

        Image(
            painter = image,
            contentDescription = "",
            modifier = imageModifier,
            contentScale = ContentScale.Crop
        )

        Text(
            text = "Sign in to your Account",
            fontWeight = FontWeight.Bold,
        )

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
            onClick = { viewModel.signIn() },
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(paddingSmall)

        ) {
            Text(text = "Login")
        }
        Spacer(modifier = Modifier.height(16.dp))

        when (loginState) {
            is Resource.Failure -> {
            }
            is Resource.Loading -> {
            }
            is Resource.Success -> {
                viewModel.resetLoginState()
                navController.navigate(Routes.HOMESCREEN)
            }
        }
    }
}