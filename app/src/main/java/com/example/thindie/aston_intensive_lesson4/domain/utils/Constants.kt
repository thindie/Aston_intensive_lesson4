package com.example.thindie.aston_intensive_lesson4.domain.utils

import android.graphics.Color

object WatchConstants {
    val digits = listOf(12, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11).map { digit -> digit.toString() }
    const val STANDART_MINUTE_HAND_WIDTH = 15F
    const val STANDART_MINUTE_HAND_HEIGHT = 180F
    const val STANDART_SECOND_HAND_WIDTH = 5F
    const val STANDART_SECOND_HAND_HEIGHT = 170F
    const val STANDART_HOUR_HAND_WIDTH =  25F
    const val STANDART_HOUR_HAND_HEIGHT = 130F
    const val WHITE_HAND = Color.WHITE
    const val BLACK_HAND = Color.BLACK
}
