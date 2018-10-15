package fuzzy

import models.FAMCell
import models.Output
import models.Variable

fun defuzzyficate(fam: ArrayList<FAMCell>, fuzzyficatedInputs: ArrayList<Variable>): ArrayList<Output> {
    val outputs = ArrayList<Output>()

    fam.forEach { cell ->
        var min = 100.0
        cell.antecedents.forEachIndexed { index, a ->
            if (fuzzyficatedInputs[index].labels[a].weight < min)
                min = fuzzyficatedInputs[index].labels[a].weight
        }
        cell.weight = min
    }

    return outputs
}