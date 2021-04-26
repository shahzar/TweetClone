package com.shahzar.twitterclone.ui.main

import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.shahzar.twitterclone.NavMgr
import com.shahzar.twitterclone.ui.base.BaseActivity
import com.shahzar.twitterclone.ui.screens.Routes
import com.shahzar.twitterclone.ui.screens.LoginScreen
import com.shahzar.twitterclone.ui.theme.MyComposeTheme
import javax.inject.Inject

class MainActivity : BaseActivity() {
    @Inject
    lateinit var navMgr: NavMgr

    private lateinit var viewModel: MainViewModel

    override fun initCompose() {
        setContent {
            MyComposeTheme {
                Surface(color = MaterialTheme.colors.background) {
                    NavigationHost()
                }
            }
        }
    }

    override fun injectDependency() = getDiComponent().inject(this)

    override fun initViews() {
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
    }

    override fun setupObservers() {
        super.setupObservers()
    }

    @Composable
    fun NavigationHost() {
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = Routes.LOGINSCREEN) {
            composable(Routes.LOGINSCREEN) {
                LoginScreen(navController, viewModel)
            }
            composable(Routes.HOMESCREEN) {
                HomeScreen()
            }
        }
    }



    @Composable
    fun HomeScreen() {
        Column {
            Text(text = "Home it is.")
        }
    }
}