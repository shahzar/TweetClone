package com.shahzar.twitterclone.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.shahzar.twitterclone.R
import com.shahzar.twitterclone.data.model.Tweet
import com.shahzar.twitterclone.ui.main.MainViewModel
import com.shahzar.twitterclone.util.Resource

@Composable
fun HomeScreen(navController: NavHostController, viewModel: MainViewModel) {

    val tweets: Resource<List<Tweet>> by viewModel.tweets.observeAsState(Resource.None())
    val newTweetContent: String by viewModel.newTweetContent.observeAsState("")

    val paddingSmall = dimensionResource(id = R.dimen.padding_small)
    val paddingDef = dimensionResource(id = R.dimen.padding_default)

    viewModel.getTweets()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(paddingDef),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        when (tweets) {
            is Resource.Success -> {
                Box(Modifier.weight(1f)) {
                    TweetList(list = (tweets as Resource.Success).data)
                }
            }
            else -> {
            }
        }

        Spacer(modifier = Modifier.height(16.dp))


        Row(verticalAlignment = Alignment.CenterVertically) {
            TextField(
                value = newTweetContent,
                onValueChange = { viewModel.onNewTweetUpdated(it) },
                label = {
                    Text(
                        text = stringResource(R.string.msg_tweet),
                        modifier = Modifier.padding(paddingSmall),
                    )
                },
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Transparent
                )
            )
            Icon(
                painter = painterResource(id = R.drawable.ic_baseline_send_24),
                contentDescription = "Post tweet",
                modifier = Modifier.clickable(onClick = {
                    if (newTweetContent.isNotEmpty()) {
                        viewModel.postTweet()
                    }
                })
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

    }
}

@Composable
private fun TweetList(list: List<Tweet>) {

    LazyColumn() {
        items(list) {
            TweetRow(tweet = it) {}
        }
    }
}

@Composable
private fun TweetRow(tweet: Tweet, onUserClick: (Tweet) -> Unit) {
    Row(
        modifier = Modifier
            .clickable(onClick = { onUserClick(tweet) })
            .fillMaxWidth()
            .padding(8.dp)
    ) {

        val imageModifier = Modifier
            .defaultMinSize(46.dp)
            .clip(shape = CircleShape)
        val image = painterResource(id = R.drawable.ic_baseline_person_24)

        Image(
            painter = image,
            contentDescription = "",
            modifier = imageModifier,
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier.padding(start = 8.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = tweet?.name ?: "User",
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.h6,
                textAlign = TextAlign.Start
            )
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                Text(
                    text = tweet?.tweet ?: "",
                    style = MaterialTheme.typography.body2,
                    textAlign = TextAlign.Start,
                )
            }

        }
    }
}

