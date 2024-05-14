package com.muen.gamesnake.presentation.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavHostController
import com.muen.gamesnake.R
import com.muen.gamesnake.data.cache.GameCache
import com.muen.gamesnake.data.model.HighScore
import com.muen.gamesnake.domain.base.TOP_10
import com.muen.gamesnake.presentation.component.AppBar
import com.muen.gamesnake.presentation.component.TitleLarge
import com.muen.gamesnake.presentation.theme.padding16dp
import com.muen.gamesnake.presentation.theme.padding8dp

@Composable
fun HighScoreScreen(navController: NavHostController) {
    val dataStore = GameCache(LocalContext.current)
    val highScores =
        dataStore.getHighScores.collectAsState(initial = listOf()).value.sortedByDescending { it.score }
            .take(TOP_10)
    AppBar(
        title = stringResource(R.string.high_score),
        onBackClicked = { navController.popBackStack() }) { contentPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = contentPadding.calculateTopPadding(),
                    bottom = padding16dp,
                    start = padding16dp,
                    end = padding16dp
                )
               // .border(width = border2dp, color = MaterialTheme.colorScheme.onBackground),
        ) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(padding16dp)
            ) {
                TitleLarge(
                    text = stringResource(R.string.player_name),
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center
                )
                TitleLarge(
                    text = stringResource(R.string.score),
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center
                )
            }
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(items = highScores) {
                    HighScoreItem(it)
                }
            }
        }
    }

}

@Composable
private fun HighScoreItem(highScore: HighScore) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(padding8dp)
    ) {
        TitleLarge(
            text = highScore.playerName,
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Center
        )
        TitleLarge(
            text = highScore.score.toString(),
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Center
        )
    }
}