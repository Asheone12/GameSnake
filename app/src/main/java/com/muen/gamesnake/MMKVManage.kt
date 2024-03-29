package com.muen.gamesnake

import com.tencent.mmkv.MMKV

object MMKVManage {
    private val mmkv = MMKV.defaultMMKV()
    //缓存变量
    private const val KEY_START_X = "startX"
    private const val KEY_START_Y = "startY"

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

}