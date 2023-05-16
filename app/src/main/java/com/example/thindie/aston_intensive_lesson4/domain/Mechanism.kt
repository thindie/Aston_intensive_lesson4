package com.example.thindie.aston_intensive_lesson4.domain

import com.example.thindie.aston_intensive_lesson4.domain.utils.HandType
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


internal class Mechanism private constructor() {
    private var secondsCounter = 0
    private var minutesCounter = 0

    fun move(): Flow<HandType> {

        return flow {
            while (true) {
                delay(SECOND)
                emit(HandType.SECOND)
                secondsCounter++
                if (secondsCounter == MINUTE) {
                    minutesCounter++;
                    secondsCounter = 0;
                    emit(HandType.MINUTE)
                }
                if (minutesCounter == HOUR) {
                    minutesCounter = 0;
                    secondsCounter = 0
                    emit(HandType.HOUR)
                }
            }
        }
    }


    companion object {
        fun getInstance(watch: Watch) {
            watch.mechanism = Mechanism()
        }

        private const val SECOND = 1000L
        private const val MINUTE = 60
        private const val HOUR = 12
    }
}

/**
 * 1. get current time
 * 2. count
 * 3. emit
 *when (clockHand.handType) {
HandType.SECOND -> withContext(scope.coroutineContext) {
while (true) {
emit(HandType.SECOND); delay(
SECONDS_HAND_DELAY
)
Log.d("SERVICE_TAG", "SEC")
}
}
HandType.MINUTE -> withContext(scope.coroutineContext) {
while (true) {
emit(HandType.MINUTE); delay(
MINUTES_HAND_DELAY
)
Log.d("SERVICE_TAG", "MIN")
}
}
HandType.HOUR -> withContext(scope.coroutineContext) {
while (true) {
emit(HandType.HOUR); delay(
HOURS_HAND_DELAY
)
Log.d("SERVICE_TAG", "HO")
}
}
}
 *
 *
 *   c = 2nr = 0
 *   0----------------------------------------------------------------
 *   0 = 2nr / 2
 *
 */