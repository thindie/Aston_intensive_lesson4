package com.example.thindie.aston_intensive_lesson4.domain

import android.graphics.Color
import com.example.thindie.aston_intensive_lesson4.domain.utils.Coordinate
import com.example.thindie.aston_intensive_lesson4.domain.utils.HandType
import com.example.thindie.aston_intensive_lesson4.domain.utils.LineCoordinate
import java.util.*
import kotlin.math.cos
import kotlin.math.sin

@Suppress("LongParameterList")
internal class ClockFace private constructor(
    private val watch: Watch,
    val radii: Float = watch.width.div(2),
    val center: Coordinate = Coordinate(
        watch.startPointX + radii,
        watch.startPointY + radii
    ),
    twelweOclock: Coordinate = Coordinate(center.X, 0F),
    val color: Int,
    val digits: List<String>,
    val digitsColor: Int = Color.BLACK
) {

    private var lastMinuteHandCoordinate = LineCoordinate(center, twelweOclock)
    private var lastHourCoordinate = LineCoordinate(center, twelweOclock)
    private var lastSecondCoordinate = LineCoordinate(center, twelweOclock)

    init {
        watch.hands.forEach { hand ->
            updateCoordinate(hand)
        }
    }

    fun getHandCoordinates(handType: HandType): List<Pair<ClockHand, LineCoordinate>> {
        updateCoordinate(watch.hands.find { it.handType == handType })
        return watch.hands.map { clockHand ->
            when (clockHand.handType) {
                HandType.MINUTE -> {
                    clockHand to lastMinuteHandCoordinate
                }
                HandType.HOUR -> {
                    clockHand to lastHourCoordinate
                }
                HandType.SECOND -> {
                    clockHand to lastSecondCoordinate
                }
            }
        }
    }

    private fun updateCoordinate(hand: ClockHand?) {
        val calendar: Calendar = Calendar.getInstance()
        var hour = calendar.get(Calendar.HOUR_OF_DAY)

        hour = if (hour > NOON) hour - NOON else hour
        val minute = calendar.get(Calendar.MINUTE)
        val second = calendar.get(Calendar.SECOND)
        when (requireNotNull(hand).handType) {
            HandType.MINUTE -> {
                val angle = Math.PI * minute / WATCH_FROM_12_TO_6 - Math.PI / 2
                val endPointX = (center.X + cos(angle) * hand.height).toFloat()
                val endPointY = (center.Y + sin(angle) * hand.height).toFloat()
                lastMinuteHandCoordinate = LineCoordinate(center, Coordinate(endPointX, endPointY))
            }
            HandType.HOUR -> {
                val angle =
                    Math.PI * ((hour + minute / MINUTES_IN_HOUR) * 5f) / WATCH_FROM_12_TO_6 - Math.PI / 2
                val endPointX = (center.X + cos(angle) * hand.height).toFloat()
                val endPointY = (center.Y + sin(angle) * hand.height).toFloat()
                lastHourCoordinate = LineCoordinate(center, Coordinate(endPointX, endPointY))
            }
            HandType.SECOND -> {
                val angle = Math.PI * second / WATCH_FROM_12_TO_6 - Math.PI / 2
                val endPointX = (center.X + cos(angle) * hand.height).toFloat()
                val endPointY = (center.Y + sin(angle) * hand.height).toFloat()
                lastSecondCoordinate = LineCoordinate(center, Coordinate(endPointX, endPointY))
            }
        }
    }


    companion object {
        private val colorList = listOf(
            Color.BLACK to Color.WHITE,
            Color.MAGENTA to Color.CYAN,
            Color.RED to Color.GREEN,
            Color.YELLOW to Color.GRAY
        )
        private const val NOON = 12
        private const val MINUTES_IN_HOUR = 60.0
        private const val WATCH_FROM_12_TO_6 = 30
        fun reveal(watch: Watch, digits: List<String>, color: Int): ClockFace {
            return ClockFace(watch = watch, color = color, digits = digits)
        }
    }

}