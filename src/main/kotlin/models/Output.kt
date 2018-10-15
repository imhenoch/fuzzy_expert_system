package models

import files.BinaryObject
import files.DataSpecification
import files.Register

data class Output(
        var outputName: String = "",
        var points: ArrayList<Point> = ArrayList(),
        var weight: Double = 0.0
) : BinaryObject("output") {
    fun fetch(): ArrayList<Register<Output>> {
        val outputs = ArrayList<Register<Output>>()

        val fileData = fetchAll()
        fileData.forEach { fd ->
            val outputName = (fd.data[0] as String).replace("*", "")
            val points = ArrayList<Point>()
            var counter = 1
            while (counter < fd.data.size) {
                if (fd.data[counter] == -1)
                    break
                points.add(Point(fd.data[counter++] as Int, fd.data[counter++] as Int))
            }
            outputs.add(Register(fd.id, fd.position, Output(outputName, points)))
        }

        return outputs
    }

    override fun dataSpecification(): List<DataSpecification> {
        val ds = ArrayList<DataSpecification>()
        ds.add(DataSpecification(outputName, String::class, 50))
        for (i in 0..9) {
            if (i < points.size) {
                ds.add(DataSpecification(points[i].x, Int::class))
                ds.add(DataSpecification(points[i].y, Int::class))
            } else {
                ds.add(DataSpecification(-1, Int::class))
                ds.add(DataSpecification(-1, Int::class))
            }
        }
        return ds
    }

    override fun toString() = outputName
}