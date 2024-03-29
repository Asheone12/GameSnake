package com.muen.gamesnake.presentation.component

import android.view.MotionEvent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.motionEventSpy
import com.muen.gamesnake.MMKVManage
import com.muen.gamesnake.data.model.State
import com.muen.gamesnake.domain.game.GameEngine
import com.muen.gamesnake.domain.game.SnakeDirection
import com.muen.gamesnake.presentation.theme.DarkGreen
import com.muen.gamesnake.presentation.theme.border2dp
import com.muen.gamesnake.presentation.theme.corner4dp
import com.muen.gamesnake.presentation.theme.padding16dp
import kotlin.math.abs

@Composable
fun Board(state: State,onDirectionChange: (Int) -> Unit) {
    BoxWithConstraints(Modifier.padding(padding16dp)) {
        val tileSize = maxWidth / GameEngine.BOARD_SIZE
        Box(
            Modifier
                .size(maxWidth)
                .border(border2dp, DarkGreen)
                .motionEventSpy {
                    when(it.action){
                        MotionEvent.ACTION_DOWN -> {
                            MMKVManage.startX = it.x
                            MMKVManage.startY = it.y
                        }
                        MotionEvent.ACTION_UP -> {
                            val endX = it.x
                            val endY = it.y
                            val dx = endX - MMKVManage.startX
                            val dy = endY - MMKVManage.startY
                            if (abs(dx) > abs(dy)){
                                //水平移动更多
                                if(dx > 0){
                                    onDirectionChange.invoke(SnakeDirection.Right)
                                }else{
                                    onDirectionChange.invoke(SnakeDirection.Left)
                                }
                            }else if(abs(dx)< abs(dy)){
                                //竖直移动更多
                                if(dy > 0){
                                    onDirectionChange.invoke(SnakeDirection.Down)
                                }else{
                                    onDirectionChange.invoke(SnakeDirection.Up)
                                }
                            }

                        }
                    }
                }
        )
        Box(
            Modifier
                .offset(x = tileSize * state.food.first, y = tileSize * state.food.second)
                .size(tileSize)
                .background(
                    DarkGreen, CircleShape
                )
        )
        state.snake.forEach {
            Box(
                modifier = Modifier
                    .offset(x = tileSize * it.first, y = tileSize * it.second)
                    .size(tileSize)
                    .background(
                        DarkGreen, RoundedCornerShape(corner4dp)
                    )
            )
        }
    }
}