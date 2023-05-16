package com.example.thindie.aston_intensive_lesson4.ui

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View
import com.example.thindie.aston_intensive_lesson4.R
import com.example.thindie.aston_intensive_lesson4.domain.ClockHand
import com.example.thindie.aston_intensive_lesson4.domain.Watch
import com.example.thindie.aston_intensive_lesson4.domain.utils.LineCoordinate
import kotlin.math.cos
import kotlin.math.sin

class WatchView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    private val watch: Watch? = ((context as? ClockActivity) as? WatchActivity)?.watch
    private val hands: List<Pair<ClockHand, LineCoordinate>> =
        ((context as? ClockActivity) as? WatchActivity)!!.watchHands

    private val paint: Paint = Paint()

    init {
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.ClockView,
            0, 0
        ).apply {
            try {
                paint.strokeWidth = getDimension(R.styleable.ClockView_strokeWidth, 5f)
                paint.color = getColor(R.styleable.ClockView_strokeColor, Color.MAGENTA)
            } finally {
                recycle()
            }
        }
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        drawWatches(canvas)
        drawHands(canvas, hands)
        drawNumerals(canvas)
        postInvalidateDelayed(500)
    }

    private fun drawWatches(canvas: Canvas?) {
        setPaintAttributes(
            requireNotNull(watch).clockFace.color,
            stroke = Paint.Style.FILL_AND_STROKE,
            strokeWidth = 10f
        )
        val clock = watch
        requireNotNull(canvas).drawCircle(
            clock.center.X,
            clock.center.Y,
            clock.clockFace.radii,
            paint
        )
    }

    /**
     * Не красиво. половине метода лежать бы на домене
     * не успел.
     */
    private fun drawNumerals(canvas: Canvas?) {
        setPaintAttributes(
            color = requireNotNull(watch).clockFace.digitsColor,
            stroke = Paint.Style.FILL_AND_STROKE,
            strokeWidth = 2f
        )
        requireNotNull(watch)
        paint.textSize = 50f
        watch.clockFace.digits.forEach { digit ->
            paint.getTextBounds(digit, 0, digit.length, Rect())
            val angle = Math.PI / 6 * (digit.toInt() - 3)
            val x =
                (watch.center.X + cos(angle) * watch.clockFace.radii ).toInt()
            val y =
                (watch.center.Y + sin(angle) * watch.clockFace.radii ).toInt()
            requireNotNull(canvas).drawText(digit, x.toFloat(), y.toFloat(), paint)
        }


    }

    private fun drawHands(canvas: Canvas?, handList: List<Pair<ClockHand, LineCoordinate>>) {
        handList.forEach { handAndCoordinates ->

            val hand = handAndCoordinates.first
            val coordinateStart = handAndCoordinates.second.start
            val coordinateEnd = handAndCoordinates.second.end

            setPaintAttributes(hand.color, Paint.Style.STROKE, hand.width)
            requireNotNull(canvas).drawLine(
                coordinateStart.X,
                coordinateStart.Y,
                coordinateEnd.X,
                coordinateEnd.Y,
                paint
            )
        }

    }

    private fun setPaintAttributes(color: Int, stroke: Paint.Style, strokeWidth: Float) {
        paint.apply {
            reset()
            setColor(color)
            style = stroke
            setStrokeWidth(strokeWidth)
            isAntiAlias = true
        }
    }
}

internal interface WatchActivity {
    val watch: Watch?
    val watchHands: List<Pair<ClockHand, LineCoordinate>>
}




