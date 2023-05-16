package com.example.thindie.aston_intensive_lesson4.domain

import android.graphics.Color
import com.example.thindie.aston_intensive_lesson4.domain.utils.Coordinate
import com.example.thindie.aston_intensive_lesson4.domain.utils.HandType
import com.example.thindie.aston_intensive_lesson4.domain.utils.WatchConstants

@Suppress("LongParameterList")
class Watch private constructor(
    val startPointX: Float,
    val startPointY: Float,
    val width: Float,
    internal val hands: List<ClockHand>,
    private val digits: List<String>,
    private val color: Int,
) {

    internal lateinit var mechanism: Mechanism

    init {
        Mechanism.getInstance(this)
    }

    internal val clockFace: ClockFace = ClockFace.reveal(this, digits, color)
    internal val center: Coordinate = clockFace.center


    companion object {
        private var width: Float = 200f
        private var startPointX: Float = 0f
        private var startPointY: Float = 0f
        private var handColor: Int = WatchConstants.BLACK_HAND
        private var color: Int = Color.MAGENTA


        private var secondHand: ClockHand = ClockHand(
            handType = HandType.SECOND,
            width = WatchConstants.STANDART_SECOND_HAND_WIDTH,
            height = WatchConstants.STANDART_SECOND_HAND_HEIGHT,
            color = handColor
        )
        private var minuteHand: ClockHand = ClockHand(
            handType = HandType.MINUTE,
            width = WatchConstants.STANDART_MINUTE_HAND_WIDTH,
            height = WatchConstants.STANDART_MINUTE_HAND_HEIGHT,
            color = handColor
        )
        private var hourHand: ClockHand = ClockHand(
            handType = HandType.HOUR,
            width = WatchConstants.STANDART_HOUR_HAND_WIDTH,
            height = WatchConstants.STANDART_HOUR_HAND_HEIGHT,
            color = handColor
        )

        fun setSide(width: Float): Companion {
            Watch.width = width
            return this
        }

        fun setColor(color: Int): Companion {
            Watch.color = color
            return this
        }

        fun setStartPointX(startPointX: Float): Companion {
            Watch.startPointX = startPointX
            return this
        }

        fun setStartPointY(startPointY: Float): Companion {
            Watch.startPointY = startPointY
            return this
        }


        internal fun setHand(
            handType: HandType,
            color: Int = handColor,
            handWidth: Float,
            handHeight: Float
        ): Companion {
            when (handType) {
                HandType.SECOND -> {
                    secondHand =
                        secondHand.copy(color = color, width = handWidth, height = handHeight)
                    //todo() addition conditions @see radii
                }
                HandType.MINUTE -> {
                    minuteHand =
                        minuteHand.copy(color = color, width = handWidth, height = handHeight)
                }
                HandType.HOUR -> {
                    hourHand = hourHand.copy(color = color, width = handWidth, height = handHeight)
                }
            }
            return this
        }


        fun Companion.build(
        ): Watch {
            return Watch(
                startPointX = startPointX,
                startPointY = startPointY,
                width = width,
                hands = listOf(secondHand, minuteHand, hourHand),
                digits = WatchConstants.digits,
                color = color
            )
        }


    }
}