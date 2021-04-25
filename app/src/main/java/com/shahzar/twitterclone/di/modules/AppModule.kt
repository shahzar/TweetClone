package com.shahzar.twitterclone.di.modules

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.shahzar.twitterclone.NavMgr
import com.shahzar.twitterclone.R
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(val application: Application) {

    @Provides
    fun getString(): String {
        return application.getString(R.string.app_name)
    }

    @Provides
    fun getContext(): Context {
        return application
    }

    @Singleton
    @Provides
    fun getNavMgr(): NavMgr {
        return NavMgr()
    }


}