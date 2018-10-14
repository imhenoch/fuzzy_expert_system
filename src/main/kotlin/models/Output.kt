package models

import files.BinaryObject
import files.DataSpecification
import files.Register

data class Output(
        var variableName: String = "",
        var points: ArrayList<Point> = ArrayList()
) : BinaryObject("output") {
    fun fetch(): ArrayList<Register<Output>> {
        val outputs = ArrayList<Register<Output>>()

        val fileData = fetchAll()
        fileData.forEach { fd ->
            val variableName = fd.data[0] as String
            val points = ArrayList<Point>()
            var counter = 1
            while (counter < fd.data.size)
                points.add(Point(fd.data[counter++] as Int, fd.data[counter++] as Int))
            outputs.add(Register(fd.id, fd.position, Output(variableName, points)))
        }

        return outputs
    }

    override fun dataSpecification(): List<DataSpecification> {
        val ds = ArrayList<DataSpecification>()
        ds.add(DataSpecification(variableName, String::class, 50))
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
}