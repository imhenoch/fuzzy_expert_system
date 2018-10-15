package models

import files.BinaryObject
import files.DataSpecification
import files.Register

data class Range(
        val min: Int,
        val max: Int,
        val output: Register<Output>
) : BinaryObject("range") {
    override fun dataSpecification(): List<DataSpecification> {
        val ds = ArrayList<DataSpecification>()

        ds.add(DataSpecification(min, Int::class))
        ds.add(DataSpecification(max, Int::class))
        ds.add(DataSpecification(output.id, Long::class))

        return ds
    }
}