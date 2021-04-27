package com.shahzar.twitterclone.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*

open class BaseViewModel : ViewModel(), CoroutineScope {

    private val job = Job()
    override val coroutineContext = job + Dispatchers.Main

    protected val _onError = MutableLiveData<String>()

    val onError: LiveData<String>
        get() = _onError

    fun showError(msg: String) {
        _onError.value = msg
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }

}