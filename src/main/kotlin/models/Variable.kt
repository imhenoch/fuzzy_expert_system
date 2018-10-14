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
        fileData.forEach { fd ->
            val variableName = (fd.data[0] as String).replace("*", "")
            val labels = ArrayList<Label>()
            var counter = 1
            while (counter < fd.data.size) {
                if ((fd.data[counter] as String)[0] == '*')
                    break
                else
                    labels.add(Label((fd.data[counter] as String).replace("*", "")))
                val points = ArrayList<Point>()
                counter++
                for (i in 0 until 10) {
                    if (fd.data[counter] != -1)
                        points.add(Point(fd.data[counter++] as Int, fd.data[counter++] as Int))
                    else
                        counter += 2
                }
                labels.last().points = points
            }
            variables.add(Register(fd.id, fd.position, Variable(variableName, labels)))
        }

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