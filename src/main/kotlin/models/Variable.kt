package models

import files.DataSpecification
import files.BinaryObject

data class Variable(
        var variableName: String = "",
        var points: ArrayList<Point> = ArrayList()
) : BinaryObject("variable") {
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