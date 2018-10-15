package models

data class Label(
        var labelName: String = "",
        var points: ArrayList<Point> = ArrayList(),
        var weight: Double = 0.0
)