package com.example.thindie.aston_intensive_lesson4.ui

import android.graphics.Color
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.thindie.aston_intensive_lesson4.domain.ClockHand
import com.example.thindie.aston_intensive_lesson4.domain.Watch
import com.example.thindie.aston_intensive_lesson4.domain.Watch.Companion.build
import com.example.thindie.aston_intensive_lesson4.domain.utils.HandType
import com.example.thindie.aston_intensive_lesson4.domain.utils.LineCoordinate
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

internal class CLockViewModel : ViewModel() {
    private val scope: CoroutineScope = CoroutineScope(Dispatchers.IO)
    private val watch: Watch = Watch
        .setSide(500f)
        .setStartPointY(300f)
        .setStartPointX(300f)
        .setHand(handType = HandType.SECOND, color = Color.RED, handWidth = 8f, handHeight = 200f)
        .setColor(Color.LTGRAY)
        .build()

    private val _tickingWatch: MutableLiveData<HandType> = MutableLiveData()
    internal val tickingWatch: LiveData<HandType>
        get() = _tickingWatch

    init {
        clockTicking()
    }

    private fun clockTicking() {
        watch
            .mechanism
            .move()
            .onEach { handType -> _tickingWatch.postValue(handType) }
            .launchIn(scope)
    }

    fun onRequestCoordinates(handType: HandType): List<Pair<ClockHand, LineCoordinate>> {
        return requireNotNull(watch.clockFace.getHandCoordinates(handType))
    }

    fun getWatch(): Watch {
        return watch
    }
}