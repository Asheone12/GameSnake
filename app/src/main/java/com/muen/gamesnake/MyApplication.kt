package com.muen.gamesnake

import android.app.Application
import com.tencent.mmkv.MMKV

class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        //初始化MMKV，返回缓存地址
        MMKV.initialize(this)
    }
}