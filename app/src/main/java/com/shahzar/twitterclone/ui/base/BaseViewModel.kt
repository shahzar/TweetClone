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

    fun <T> ioLaunch(block: suspend () -> T, onSuccess: (T) -> Unit = {}, onFailure: ((t:Throwable) -> Unit)? = null) = launch {

        runCatching {
            block.invoke()
        }
        .onFailure {
            if (onFailure == null) {
                _onError.value = it.message
            }
            onFailure?.invoke(it)
        }
        .onSuccess {
            onSuccess.invoke(it)
        }
    }

    fun showError(msg: String) {
        _onError.value = msg
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }

}