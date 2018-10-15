package models

import files.BinaryObject
import files.DataSpecification
import files.Register

data class Range(
        val min: Int = 0,
        val max: Int = 0,
        val output: Register<Output> = Register(0)
) : BinaryObject("range") {
    fun fetch(): ArrayList<Register<Range>> {
        val ranges = ArrayList<Register<Range>>()

        val fileData = fetchAll()
        var counter = 0
        fileData.forEach { fd ->
            val min = fd.data[counter++] as Int
            val max = fd.data[counter++] as Int
            val output = Register<Output>(fd.data[counter++] as Long)
            ranges.add(Register(fd.id, fd.position, Range(min, max, output)))
        }

        return ranges
    }

    override fun dataSpecification(): List<DataSpecification> {
        val ds = ArrayList<DataSpecification>()

        ds.add(DataSpecification(min, Int::class))
        ds.add(DataSpecification(max, Int::class))
        ds.add(DataSpecification(output.id, Long::class))

        return ds
    }
}