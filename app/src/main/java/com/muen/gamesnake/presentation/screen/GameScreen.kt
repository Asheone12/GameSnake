package com.muen.gamesnake.presentation.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.muen.gamesnake.R
import com.muen.gamesnake.domain.game.GameEngine
import com.muen.gamesnake.domain.game.SnakeDirection
import com.muen.gamesnake.presentation.activity.GameActivity
import com.muen.gamesnake.presentation.component.AppBar
import com.muen.gamesnake.presentation.component.Board
import com.muen.gamesnake.presentation.theme.padding48dp

@Composable
fun GameScreen(gameEngine: GameEngine, score: Int) {
    val state = gameEngine.state.collectAsState(initial = null)
    val activity = LocalContext.current as GameActivity
    AppBar(
        title = stringResource(id = R.string.your_score, score),
        onBackClicked = { activity.finish() }) { contentPadding ->
        Column(
            modifier = Modifier.padding(top = padding48dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            state.value?.let { Board(it){direct->
                when (direct) {
                    SnakeDirection.Up -> gameEngine.move = Pair(0, -1)
                    SnakeDirection.Left -> gameEngine.move = Pair(-1, 0)
                    SnakeDirection.Right -> gameEngine.move = Pair(1, 0)
                    SnakeDirection.Down -> gameEngine.move = Pair(0, 1)
                }
            } }
            /*Controller {
                when (it) {
                    SnakeDirection.Up -> gameEngine.move = Pair(0, -1)
                    SnakeDirection.Left -> gameEngine.move = Pair(-1, 0)
                    SnakeDirection.Right -> gameEngine.move = Pair(1, 0)
                    SnakeDirection.Down -> gameEngine.move = Pair(0, 1)
                }
            }*/
        }
    }
}