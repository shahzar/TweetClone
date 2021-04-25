package com.shahzar.twitterclone.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.shahzar.twitterclone.BaseApplication
import com.shahzar.twitterclone.di.components.AppComponent
import com.shahzar.twitterclone.di.ViewModelFactory
import javax.inject.Inject

abstract class BaseActivity: AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    abstract fun getLayoutRes(): Int
    abstract fun injectDependency()
    abstract fun initViews()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutRes())
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