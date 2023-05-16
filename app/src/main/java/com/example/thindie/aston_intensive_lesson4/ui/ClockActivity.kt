package com.example.thindie.aston_intensive_lesson4.ui

import android.os.Bundle
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.thindie.aston_intensive_lesson4.R
import com.example.thindie.aston_intensive_lesson4.domain.ClockHand
import com.example.thindie.aston_intensive_lesson4.domain.Watch
import com.example.thindie.aston_intensive_lesson4.domain.utils.LineCoordinate

internal class ClockActivity : AppCompatActivity(), WatchActivity {

    private val viewModel: CLockViewModel by lazy {
        ViewModelProvider(this)[CLockViewModel::class.java]
    }
    override val watch: Watch? by lazy { viewModel.getWatch() }
    private val handsList: MutableList<Pair<ClockHand, LineCoordinate>> = mutableListOf()
    override val watchHands: List<Pair<ClockHand, LineCoordinate>>
        get() = handsList

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel
            .tickingWatch
            .observe(this) { handType ->
                handsList.clear()
                viewModel.onRequestCoordinates(handType).forEach { hand ->
                    handsList.add(hand)
                }
            }
    }
}