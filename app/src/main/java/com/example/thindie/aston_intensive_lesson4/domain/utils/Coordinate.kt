package com.example.thindie.aston_intensive_lesson4.domain.utils


@Suppress("ConstructorParameterNaming")
internal data class Coordinate(
    val X: Float,
    val Y: Float
)

internal data class LineCoordinate(
    val start: Coordinate,
    val end: Coordinate
)