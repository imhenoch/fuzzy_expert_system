package models

data class FAMCell(
        val antecedents: IntArray,
        val output: Long,
        var weight: Double = 0.0
)