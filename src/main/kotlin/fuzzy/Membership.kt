package fuzzy

import models.Point

fun getMembershipWithX(x: Int, p1: Point, p2: Point): Double {
    return ((x.toDouble() - p1.x.toDouble()) /
            (p2.x.toDouble() - p1.x.toDouble())) *
            (p2.y.toDouble() - p1.y.toDouble()) + p1.y.toDouble()
}

fun getMembershipWithY(y: Int, p1: Point, p2: Point): Double {
    return ((y.toDouble() - p1.y.toDouble()) /
            (p2.y.toDouble() - p1.y.toDouble())) *
            (p2.x.toDouble() - p1.x.toDouble()) + p1.x.toDouble()
}
