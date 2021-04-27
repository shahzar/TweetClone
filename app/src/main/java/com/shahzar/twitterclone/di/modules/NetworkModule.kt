package com.shahzar.twitterclone.di.modules

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.google.gson.GsonBuilder
import com.shahzar.twitterclone.BuildConfig
import com.shahzar.twitterclone.data.UserRepository
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    fun provideGson(): GsonConverterFactory = GsonConverterFactory.create(GsonBuilder().create())

    @Provides
    fun provideUserRepository(): UserRepository{
        return UserRepository()
    }

}