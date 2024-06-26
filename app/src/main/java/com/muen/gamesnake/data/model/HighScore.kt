package com.muen.gamesnake.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class HighScore(val playerName: String, var score: Int): Parcelable
