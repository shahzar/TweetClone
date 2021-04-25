package com.shahzar.twitterclone

import android.app.Application
import com.shahzar.twitterclone.di.components.AppComponent
import com.shahzar.twitterclone.di.components.DaggerAppComponent
import com.shahzar.twitterclone.di.modules.AppModule

class BaseApplication : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()
        appComponent.application(this)
    }

}