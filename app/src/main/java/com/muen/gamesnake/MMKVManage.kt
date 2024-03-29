package com.muen.gamesnake

import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.muen.gamesnake.data.model.HighScore
import com.tencent.mmkv.MMKV

object MMKVManage {
    private val mmkv = MMKV.defaultMMKV()
    //缓存变量
    private const val KEY_START_X = "startX"
    private const val KEY_START_Y = "startY"
    private const val KEY_PLAYER_NAME = "player_name"
    private const val KEY_HIGH_SCORES = "high_scores"


    /**
     * 起始x坐标
     */
    var startX: Float
        set(value) {
            mmkv.encode(KEY_START_X, value)
        }
        get() = mmkv.decodeFloat(KEY_START_X)

    /**
     * 起始y坐标
     */
    var startY:Float
        set(value) {
            mmkv.encode(KEY_START_Y, value)
        }
        get() = mmkv.decodeFloat(KEY_START_Y)

    /**
     * 玩家姓名
     */
    var playerName: String
        set(value) {
            mmkv.encode(KEY_PLAYER_NAME, value)
        }
        get() = mmkv.decodeString(KEY_PLAYER_NAME) ?: "玩家"

    /**
     * 最高分排行
     */
    var highScores: List<HighScore>
        set(value) {
            Log.d("123","MMKV higScores = ${Gson().toJson(value)}")
            mmkv.encode(KEY_HIGH_SCORES, Gson().toJson(value))
        }
        get() = returnHighScores()

    private fun returnHighScores():List<HighScore>{
        val scores = mmkv.decodeString(KEY_HIGH_SCORES) ?:""
        Log.d("123","MMKV return highScores = $scores")
        val listType = object : TypeToken<List<HighScore?>?>() {}.type
        return Gson().fromJson<List<HighScore>>(scores, listType) ?: listOf()
    }

}