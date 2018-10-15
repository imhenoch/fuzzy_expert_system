package fuzzy

import files.Register
import models.FAMCell
import models.Variable

fun generateFAM(variables: ArrayList<Register<Variable>>): ArrayList<FAMCell> {
    val FAM = ArrayList<FAMCell>()

    val limiters = IntArray(variables.size) { i ->
        variables[i].data!!.labels.size
    }
    val counters = IntArray(variables.size) { 0 }

    while (true) {
        if (counters[0] == limiters[0])
            break

        val antecedents = IntArray(variables.size) { i -> counters[i] }
        FAM.add(FAMCell(antecedents, 0))

        incrementCounters(counters, limiters, counters.lastIndex)
    }

    return FAM
}

private fun incrementCounters(counters: IntArray, limiters: IntArray, index: Int) {
    counters[index] += 1
    if (counters[index] == limiters[index]) {
        if (index != 0) {
            counters[index] = 0
            incrementCounters(counters, limiters, index - 1)
        }
    }
}