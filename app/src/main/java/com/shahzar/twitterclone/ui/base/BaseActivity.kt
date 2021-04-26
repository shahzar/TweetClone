package com.shahzar.twitterclone.ui.base

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import com.shahzar.twitterclone.BaseApplication
import com.shahzar.twitterclone.di.components.AppComponent
import com.shahzar.twitterclone.di.ViewModelFactory
import com.shahzar.twitterclone.ui.theme.MyComposeTheme
import javax.inject.Inject

abstract class BaseActivity: ComponentActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    abstract fun initCompose()
    abstract fun injectDependency()
    abstract fun initViews()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initCompose()
        injectDependency()
        initViews()
        setupObservers()
    }


    open fun setupObservers() {

    }

    fun getDiComponent() : AppComponent {
        return (application as BaseApplication).appComponent
    }

}