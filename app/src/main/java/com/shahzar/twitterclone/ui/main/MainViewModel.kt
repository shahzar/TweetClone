package com.shahzar.twitterclone.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseUser
import com.shahzar.twitterclone.data.UserRepository
import com.shahzar.twitterclone.data.model.Tweet
import com.shahzar.twitterclone.ui.base.BaseViewModel
import com.shahzar.twitterclone.util.Resource
import javax.inject.Inject


class MainViewModel @Inject constructor (
    private val userRepository: UserRepository
): BaseViewModel() {

    private val _email = MutableLiveData("")
    val email: LiveData<String> = _email

    private val _password = MutableLiveData("")
    val password: LiveData<String> = _password

    private val _loginState = MutableLiveData<Resource<FirebaseUser>>()
    val loginState: LiveData<Resource<FirebaseUser>> = _loginState

    private val _tweets = MutableLiveData<Resource<List<Tweet>>>()
    val tweets: LiveData<Resource<List<Tweet>>> = _tweets

    private val _newTweetContent = MutableLiveData<String>()
    val newTweetContent: LiveData<String> = _newTweetContent

    init {
        getTweets()
    }

    fun onEmailChange(newName: String) {
        _email.value = newName
    }

    fun onPasswordChange(newName: String) {
        _password.value = newName
    }

    fun resetLoginState() {
        _loginState.value = Resource.None()
    }

    fun onNewTweetUpdated(tweetContent: String) {
        _newTweetContent.value = tweetContent
    }

    fun signIn() {
        val email = _email.value
        val password = _password.value

        if (email.isNullOrEmpty() || password.isNullOrEmpty()) {
            return
        }

        _loginState.value = Resource.Loading()

        userRepository.signIn(email, password, onResponse = {
            _loginState.value = it
        })
        return
    }

    fun getTweets() {
        Log.d("Data", "get called!")
        userRepository.getTweets {
           _tweets.value = it
        }
    }

    fun postTweet() {
        userRepository.postTweet(newTweetContent.value, onSuccessResponse = {
            _newTweetContent.value = ""
        })
    }


}