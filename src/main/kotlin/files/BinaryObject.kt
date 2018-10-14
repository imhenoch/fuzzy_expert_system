package files

import java.io.RandomAccessFile

abstract class BinaryObject(fileName: String) {
    val index = RandomAccessFile("$fileName.index", "rw")
    val master = RandomAccessFile("$fileName.master", "rw")

    fun fetch() {

    }

    fun init() {
        val ds = dataSpecification()
        val (id, position) = write(master, ds)
        write(index, arrayListOf(
                DataSpecification(id, Long::class),
                DataSpecification(position, Long::class)
        ))
    }

    fun commit() {

    }

    fun delete() {

    }

    abstract fun dataSpecification(): List<DataSpecification>
}