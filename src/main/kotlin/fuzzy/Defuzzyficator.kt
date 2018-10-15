package fuzzy

import files.Register
import models.FAMCell
import models.Output
import models.Variable

fun defuzzyficate(
        fam: ArrayList<FAMCell>, fuzzyficatedInputs: ArrayList<Variable>, outputs: ArrayList<Register<Output>>
): ArrayList<Output> {
    val realOutputs = ArrayList<Output>()

    fam.forEach { cell ->
        var min = 100.0
        cell.antecedents.forEachIndexed { index, a ->
            if (fuzzyficatedInputs[index].labels[a].weight < min)
                min = fuzzyficatedInputs[index].labels[a].weight
        }
        cell.weight = min
    }
    outputs.forEach { o ->
        var max = 0.0
        fam.filter { cell ->
            cell.output == o.id
        }.forEach { cell ->
            if (cell.weight > max)
                max = cell.weight
        }
        realOutputs.add(o.data!!.apply { weight = max })
    }

    return realOutputs
}