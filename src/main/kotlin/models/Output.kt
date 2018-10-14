package models

import files.BinaryObject
import files.DataSpecification

data class Output(
        var variableName: String = "",
        var points: ArrayList<Point> = ArrayList()
) : BinaryObject("output") {
    override fun dataSpecification(): List<DataSpecification> {
        val ds = ArrayList<DataSpecification>()
        ds.add(DataSpecification(variableName, String::class, 50))
        for (i in 0..9) {
            if (i < points.size) {
                ds.add(DataSpecification(points[i].x, Byte::class))
                ds.add(DataSpecification(points[i].y, Byte::class))
            } else {
                ds.add(DataSpecification(-1, Byte::class))
                ds.add(DataSpecification(-1, Byte::class))
            }
        }
        return ds
    }
}