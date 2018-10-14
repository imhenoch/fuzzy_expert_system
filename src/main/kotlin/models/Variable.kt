package models

import files.BinaryObject
import files.DataSpecification
import files.Register

data class Variable(
        var variableName: String = "",
        var labels: ArrayList<Label> = ArrayList()
) : BinaryObject("variable") {
    fun fetch(): ArrayList<Register<Variable>> {
        val variables = ArrayList<Register<Variable>>()

        val fileData = fetchAll()
        println(fileData)

        return variables
    }

    override fun dataSpecification(): List<DataSpecification> {
        val ds = ArrayList<DataSpecification>()
        ds.add(DataSpecification(variableName, String::class, 50))
        for (i in 0..9) {
            if (i < labels.size) {
                labels[i].let { l ->
                    ds.add(DataSpecification(l.labelName, String::class, 50))
                    for (j in 0..9) {
                        if (j < l.points.size) {
                            ds.add(DataSpecification(l.points[j].x, Int::class))
                            ds.add(DataSpecification(l.points[j].y, Int::class))
                        } else {
                            ds.add(DataSpecification(-1, Int::class))
                            ds.add(DataSpecification(-1, Int::class))
                        }
                    }
                }
            } else {
                ds.add(DataSpecification("", String::class, 50))
                for (j in 0..9) {
                    ds.add(DataSpecification(-1, Int::class))
                    ds.add(DataSpecification(-1, Int::class))
                }
            }
        }
        return ds
    }
}