package models

data class FAMCell(
        val antecedents: IntArray,
        val output: Long,
        val weight: Double = 0.0
)