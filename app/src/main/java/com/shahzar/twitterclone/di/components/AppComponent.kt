package com.shahzar.twitterclone.di.components

import android.app.Application
import com.shahzar.twitterclone.di.ViewModelBuilder
import com.shahzar.twitterclone.di.modules.AppModule
import com.shahzar.twitterclone.di.modules.NetworkModule
import com.shahzar.twitterclone.ui.main.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, NetworkModule::class, ViewModelBuilder::class])
interface AppComponent {

    fun application(application: Application)
    fun inject(mainActivity: MainActivity)

}