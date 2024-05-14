package com.muen.gamesnake.presentation.activity

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.lifecycleScope
import com.muen.gamesnake.MMKVManage
import com.muen.gamesnake.data.model.HighScore
import com.muen.gamesnake.domain.base.BaseActivity
import com.muen.gamesnake.domain.base.TOP_10
import com.muen.gamesnake.domain.game.GameEngine
import com.muen.gamesnake.presentation.screen.EndScreen
import com.muen.gamesnake.presentation.screen.GameScreen
import kotlinx.coroutines.CoroutineScope

class GameActivity : BaseActivity() {
    private val isPlaying = mutableStateOf(true)
    private var score = mutableIntStateOf(0)
    private lateinit var scope: CoroutineScope
    private lateinit var mPlayerName: String
    private lateinit var mHighScores: List<HighScore>
    private var gameEngine = GameEngine(
        scope = lifecycleScope,
        onGameEnded = {
            if (isPlaying.value) {
                isPlaying.value = false
                var flag = false
                for (highScore in mHighScores) {
                    if (highScore.playerName == mPlayerName) {
                        if (score.value > highScore.score) {
                            highScore.score = score.value
                        }
                        flag = true
                        break
                    }
                }
                val tempList = mutableListOf<HighScore>()
                tempList.addAll(mHighScores)
                if (!flag) {
                    tempList.add(HighScore(mPlayerName, score.value))
                }
                tempList.sortByDescending{ highScore -> highScore.score }
                val newList = tempList.take(TOP_10)
                MMKVManage.highScores = newList
            }
        },
        onFoodEaten = { score.value++ }
    )

    override fun onBackPressed() {
        //屏蔽右滑返回
        return
    }

    @Composable
    override fun Content() {
        scope = rememberCoroutineScope()
        mPlayerName = MMKVManage.playerName
        mHighScores = MMKVManage.highScores
        Column {
            if (isPlaying.value) {
                GameScreen(gameEngine, score.value)
            } else {
                EndScreen(score.value) {
                    score.value = 0
                    gameEngine.reset()
                    isPlaying.value = true
                }
            }
        }
    }
}