package com.shahzar.twitterclone.data

import com.shahzar.twitterclone.data.remote.ApiService
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val remoteDataSrc: ApiService
): BaseRepository() {


}