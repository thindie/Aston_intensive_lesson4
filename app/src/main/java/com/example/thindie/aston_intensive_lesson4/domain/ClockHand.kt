package com.example.thindie.aston_intensive_lesson4.domain

import com.example.thindie.aston_intensive_lesson4.domain.utils.HandType


data class ClockHand(
    val handType: HandType,
    val width: Float,
    val height: Float,
    val color: Int
)
