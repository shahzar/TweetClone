package com.shahzar.twitterclone.data

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.shahzar.twitterclone.data.model.Tweet
import com.shahzar.twitterclone.util.Resource
import javax.inject.Inject

class UserRepository @Inject constructor(): BaseRepository() {

    private val COLLECTION_TWEETS = "tweets"

    private var auth: FirebaseAuth = Firebase.auth
    private val db = Firebase.firestore

    fun isUserLoggedIn(): Boolean {
        return auth.currentUser != null
    }

    fun signUp(email:String, password: String): MutableLiveData<FirebaseUser?> {

        val userSignup = MutableLiveData<FirebaseUser?>()

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    userSignup.postValue(auth.currentUser)
                } else {
                    userSignup.postValue(null)
                }
            }

        return userSignup
    }

    fun signIn(email: String, password: String, onResponse: (resource: Resource<FirebaseUser>) -> Unit) {

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    onResponse.invoke(Resource.Success(auth.currentUser))
                } else {
                    onResponse.invoke(Resource.Failure(task.exception?: Exception("An error occurred")))
                }
            }


    }

    fun getTweets(onResponse: (resource: Resource<List<Tweet>>) -> Unit) {
        db.collection(COLLECTION_TWEETS).addSnapshotListener { snapshot, error ->
            if (error != null) {
                return@addSnapshotListener
            }

            if (snapshot != null && !snapshot.isEmpty) {
                var list = snapshot.map { documentSnapshot ->
                    documentSnapshot.toObject(Tweet::class.java)
                }

                onResponse.invoke(Resource.Success(list))
            }

        }
    }

    fun postTweet(tweet: String?, onSuccessResponse: () -> Unit) {
        if (auth.currentUser == null) {
            return
        }

        val tweetObj = Tweet(auth?.currentUser.displayName, tweet)

        db.collection(COLLECTION_TWEETS)
            .add(tweetObj)
            .addOnSuccessListener {
                onSuccessResponse.invoke()
            }
    }


}