package fuzzy

import models.Answer
import models.Label
import models.Variable

fun fuzzyficateInput(answers: ArrayList<Answer>): ArrayList<Variable> {
    val results = ArrayList<Variable>()

    answers.forEach { a ->
        results.add(a.variable.apply { labels = fuzzyficateInput(a) })
    }

    return results
}

private fun fuzzyficateInput(answer: Answer): ArrayList<Label> {
    val result = ArrayList<Label>()
    answer.variable.labels.forEach { l ->
        if (isOnRange(l, answer.percentage)) {
            for (i in 0 until l.points.size - 1) {
                if (answer.percentage == l.points[i].x)
                    result.add(l.apply { weight = l.points[i].y.toDouble() })
                else if (answer.percentage == l.points[i + 1].x)
                    result.add(l.apply { weight = l.points[i + 1].y.toDouble() })
                else if (answer.percentage >= l.points[i].x && answer.percentage <= l.points[i + 1].x) {
                    val value = getMembership(answer.percentage, l.points[i], l.points[i + 1])
                    result.add(l.apply { weight = value })
                }
            }
        } else
            result.add(l.apply { weight = 0.0 })
    }
    return result
}

private fun isOnRange(label: Label, percentage: Int) =
        percentage >= label.points.first().x && percentage <= label.points.last().x