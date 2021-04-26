package com.shahzar.twitterclone.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.shahzar.twitterclone.ui.base.BaseViewModel
import javax.inject.Inject


class MainViewModel @Inject constructor (): BaseViewModel() {

    private val _email = MutableLiveData("")
    val email: LiveData<String> = _email

    private val _password = MutableLiveData("")
    val password: LiveData<String> = _password


    fun onEmailChange(newName: String) {
        _email.value = newName
    }

    fun onPasswordChange(newName: String) {
        _password.value = newName
    }


}