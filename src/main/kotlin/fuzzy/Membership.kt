package fuzzy

import models.Point

fun getMembership(x: Int, p1: Point, p2: Point): Double {
    return ((x.toDouble() - p1.x.toDouble()) /
            (p2.x.toDouble() - p1.x.toDouble())) *
            (p2.y.toDouble() - p1.y.toDouble()) + p1.y.toDouble()
}