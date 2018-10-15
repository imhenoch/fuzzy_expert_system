package fuzzy

import files.Register
import models.FAMCell
import models.Range
import models.Variable

fun generateFAM(variables: ArrayList<Register<Variable>>, ranges: ArrayList<Register<Range>>): ArrayList<FAMCell> {
    val FAM = ArrayList<FAMCell>()

    val limiters = IntArray(variables.size) { i ->
        variables[i].data!!.labels.size
    }
    val counters = IntArray(variables.size) { 0 }

    while (true) {
        if (counters[0] == limiters[0])
            break

        val antecedents = IntArray(variables.size) { i -> counters[i] }
        FAM.add(FAMCell(antecedents, obtainOutput(ranges, antecedents)))

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

private fun obtainOutput(ranges: ArrayList<Register<Range>>, antecedents: IntArray): Long {
    var weight = 0
    var output: Long = 0
    antecedents.forEach { a -> weight += a + 1 }
    for (i in ranges.size - 1 downTo 0) {
        if (weight >= ranges[i].data!!.min) {
            output = ranges[i].data!!.output.id
            break
        }
    }
    return output
}